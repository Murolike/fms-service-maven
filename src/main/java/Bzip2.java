import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import java.io.*;

/**
 * Класс для разорхивации файлов bz2
 */
public class Bzip2 {
    /**
     * Буфер
     */
    public static Integer BUFFER_SIZE = 2048;
    /**
     * Архивный файл
     */
    protected File archive;

    public Bzip2(File archive) {
        this.archive = archive;
    }

    /**
     * Метод для разорхивации файлов
     *
     * @return Возвращает ссылку на единственный файл внутри
     * @throws IOException Возникает когда файл не был найден
     */
    public File unzip() throws IOException {
        File file = new File(this.getAbsoluteFilePathUncompressed());
        BZip2CompressorInputStream bZipInputStream = new BZip2CompressorInputStream(new BufferedInputStream(new FileInputStream(this.archive)), true);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        final byte[] buffer = new byte[BUFFER_SIZE];
        int n;
        while (-1 != (n = bZipInputStream.read(buffer))) {
            fileOutputStream.write(buffer, 0, n);
        }
        fileOutputStream.close();
        bZipInputStream.close();

        return file;
    }

    /**
     * Метод возвращает полный путь до файла без расширения архива
     *
     * @return Возвращает полный путь до файла
     */
    protected String getAbsoluteFilePathUncompressed() {
        String path = this.archive.getAbsolutePath();
        return path.substring(0, path.lastIndexOf("."));
    }
}
