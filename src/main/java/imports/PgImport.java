package imports;

import connections.IConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PgImport extends Import {

    public static String DEFAULT_CMD_IMPORT = "LOAD CSV FROM '{fileName}' WITH ENCODING UTF-8" +
            " INTO postgresql://{user}:{password}@{host}:{port}/{dbname}?tablename={tableName}" +
            " TARGET COLUMNS (series, number)" +
            " WITH TRUNCATE, SKIP HEADER = 1, fields terminated by ',';";


    public PgImport(IConnection connection, File db) {
        this.connection = connection;
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
        if (file.exists() && !file.delete()) {
            throw new IOException("Не удалось удалить файл " + file.toPath());
        }
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file));
        outputStream.write(command);
        outputStream.close();

        return file;
    }

    protected String generateCommand(String tableName, Path path) {
        return DEFAULT_CMD_IMPORT
                .replace("{host}", this.connection.getHost())
                .replace("{port}", this.connection.getPort())
                .replace("{user}", this.connection.getUsername())
                .replace("{password}", this.connection.getPassword())
                .replace("{dbname}", this.connection.getDbName())
                .replace("{tableName}", tableName)
                .replace("{fileName}", path.toString());
    }
}
