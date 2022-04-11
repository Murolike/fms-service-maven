package imports;

import connections.IConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MySqlImport extends Import {

    public MySqlImport(IConnection connection, File db) {
        this.connection = connection;
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
                .replace("{host}", this.connection.getHost())
                .replace("{port}", this.connection.getPort())
                .replace("{user}", this.connection.getUsername())
                .replace("{password}", this.connection.getPassword())
                .replace("{fileName}", path.toString());
    }
}
