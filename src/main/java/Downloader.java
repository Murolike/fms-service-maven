import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Класс для скачивания файла с невалидными паспортами в папку
 */
public class Downloader {
    /**
     * Путь до хранилища
     */
    protected String storagePath;
    /**
     * URL для скачивания
     */
    protected URL url;

    public Downloader(String link, String storagePath) throws IOException {
        this.storagePath = storagePath;
        this.url = new URL(link);
    }

    /**
     * Выполняет скачивание файла
     * @return Возвращает объект файла
     * @throws IOException Когда не смог удалить файл или сохранить информацию
     */
    public File download() throws IOException {
        File file = new File(this.storagePath + this.getShortFileName());
        if (file.exists() && !file.delete()) {
            throw new IOException("Не могу удалить файл по пути: " + file.getAbsolutePath());
        }

        ReadableByteChannel readableByteChannel = Channels.newChannel(this.url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        return file;
    }

    /**
     * Метод для получения короткого имени файла
     * @return Возвращает имя файл с расширениями
     */
    protected String getShortFileName() {
        String fullFileName = this.url.getFile();
        return fullFileName.substring(fullFileName.lastIndexOf("/") + 1);
    }
}
