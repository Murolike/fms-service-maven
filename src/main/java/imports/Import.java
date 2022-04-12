package imports;

import connections.IConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract public class Import {
    public final static String[] TABLE_NAMES = {"invalid_passports_master", "invalid_passports_slave"};

    protected IConnection connection;
    protected File db;

    abstract public void run() throws IOException, InterruptedException;

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

    protected int executeCommand(String command) throws InterruptedException, IOException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(command);
        return pr.waitFor();
//        int resultCode = pr.waitFor();
//        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//        String line = "";
//        while ((line = buf.readLine()) != null) {
//            System.out.println(line);
//        }

//        return resultCode;
    }
}
