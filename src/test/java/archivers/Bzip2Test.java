package archivers;

import archivers.Bzip2;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Bzip2Test {

    @Test
    void unzip() throws IOException {
        String expected = "/tmp/list_of_expired_passports.csv";
        Bzip2 model = new Bzip2(new File("/tmp/list_of_expired_passports.csv.bz2"));
        File file = model.unzip();
        String actual = file.getAbsolutePath();

        assertEquals(expected, actual);
    }

    @Test
    void whenFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> {
            Bzip2 model = new Bzip2(new File("/tmp/arch.csv.bz2"));
            model.unzip();
        });
    }
}