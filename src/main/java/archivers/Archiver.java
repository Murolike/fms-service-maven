package archivers;

import java.io.File;
import java.io.IOException;

public interface Archiver {
    File unzip() throws IOException;
}
