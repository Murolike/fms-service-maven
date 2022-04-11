package imports;

import connections.Mysql;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MysqlImportTest {

    @Test
    void run() {
        assertDoesNotThrow(() -> {
            Mysql connection = new Mysql("localhost", "3306", "root", "test");
            MySqlImport model = new MySqlImport(connection, new File("/tmp/list_of_expired_passports.csv"));
            model.run();
        });
    }
}