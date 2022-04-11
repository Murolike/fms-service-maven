package imports;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MysqlImportTest {

    @Test
    void run()  {
        assertDoesNotThrow(() -> {
            Mysql model = new Mysql("localhost", "3306", "root", "test", new File("/tmp/list_of_expired_passports.csv"));
            model.run();
        });
    }
}