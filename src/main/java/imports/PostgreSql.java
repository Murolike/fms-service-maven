package imports;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PostgreSql extends Import {
    protected String dbName;

    public static String DEFAULT_CMD_IMPORT = "LOAD CSV FROM '{fileName}' WITH ENCODING UTF-8" +
            " INTO postgresql://{user}:{password}@{host}:{port}/{dbname}?tablename={tableName}" +
            " TARGET COLUMNS (series, number)" +
            " WITH TRUNCATE, SKIP HEADER = 1, fields terminated by ',';";


    public PostgreSql(String host, String port, String username, String password, String dbName, File db) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.db = db;
    }

    @Override
    public void run() throws IOException, InterruptedException {
        int resultCode;
        for (String tableName : TABLE_NAMES) {
            Path path = this.createSymlink(tableName);
            String fullCommand = this.generateCommand(tableName, path);
            File loadCsv = this.createLoadCsv(tableName, fullCommand);

            resultCode = this.executeCommand("pgloader " + loadCsv.toPath());

            Files.deleteIfExists(path);
            loadCsv.delete();

            if (0 != resultCode) {
                throw new RuntimeException("При выполнении команды " + fullCommand + " произошла ошибка - код => " + resultCode);
            }
        }
    }

    protected File createLoadCsv(String tableName, String command) throws IOException {
        File file = new File("/tmp/" + tableName + ".load");
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file));
        outputStream.write(command);
        outputStream.close();

        return file;
    }

    protected String generateCommand(String tableName, Path path) {
        return DEFAULT_CMD_IMPORT
                .replace("{host}", this.host)
                .replace("{port}", this.port)
                .replace("{user}", this.username)
                .replace("{password}", this.password)
                .replace("{dbname}", this.dbName)
                .replace("{tableName}", tableName)
                .replace("{fileName}", path.toString());
    }
}
