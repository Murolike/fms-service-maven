import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MysqlImport {
    public final static String[] TABLE_NAMES = {"invalid_passports_master", "invalid_passports_slave"};
    public static String DEFAULT_CMD_IMPORT = "mysqlimport --local -d --fields-terminated-by=, --ignore-lines=1 --host={host} --port={port} --user={user} --password={password} fms {fileName}";

    protected String host;
    protected String port;
    protected String username;
    protected String password;
    protected File db;

    public MysqlImport(String host, String port, String username, String password, File db) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.db = db;
    }

    public void run() throws IOException, InterruptedException {
        int resultCode;
        for (String tableName : TABLE_NAMES) {
            Path path = this.createSymlink(tableName);
            String fullCommand = this.generateCommand(path);
            resultCode = this.executeCommand(fullCommand);

            Files.deleteIfExists(path);

            if (0 != resultCode) {
                throw new RuntimeException("При выполнении команды " + fullCommand + " произошла ошибка - код => " + resultCode);
            }
        }
    }

    protected String generateCommand(Path path) {
        return DEFAULT_CMD_IMPORT
                .replace("{host}", this.host)
                .replace("{port}", this.port)
                .replace("{user}", this.username)
                .replace("{password}", this.password)
                .replace("{fileName}", path.toString());
    }

    protected int executeCommand(String command) throws InterruptedException, IOException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(command);
        return pr.waitFor();
    }

    protected Path createSymlink(String tableName) throws IOException {
        String linkName = this.db.getAbsolutePath().replace(this.getShortFileName(), tableName);
        Path path = Paths.get(linkName);

        Files.deleteIfExists(path);
        Files.createLink(path, this.db.toPath());

        return path;
    }

    protected String getShortFileName() {
        return this.db.getName().substring(0, this.db.getName().lastIndexOf("."));
    }
}
