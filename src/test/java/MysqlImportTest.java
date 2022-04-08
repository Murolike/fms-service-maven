import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MysqlImportTest {

    @Test
    void run()  {
        assertDoesNotThrow(() -> {
            MysqlImport model = new MysqlImport("localhost", "3306", "root", "test", new File("/tmp/list_of_expired_passports.csv"));
            model.run();
        });
    }
}