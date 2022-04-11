package downloaders;

import downloaders.Downloader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class DownloaderTest {

    @Test
    void whenLinkIncorrect() {
        assertThrows(MalformedURLException.class, () -> new Downloader("inccorectlink", "/tmp/"));
    }

    @Test
    void download() throws IOException {
        String fullFilePath = "/tmp/list_of_expired_passports.csv.bz2";
        Downloader model = new Downloader("http://localhost:8087/list_of_expired_passports.csv.bz2", "/tmp/");
        File file = model.download();
        assertEquals(fullFilePath, file.getAbsolutePath());
    }
}