package imports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Mysql extends Import {

    public Mysql(String host, String port, String username, String password, File db) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.db = db;
    }

    @Override
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
}
