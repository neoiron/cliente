package repository.io;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import repository.exception.FileSystemException;

final class DataSource {

    interface Property {
        String FILE_NAME = "io.properties";
        String SOURCE = "io.source";

        interface Permission {
            String RW = "io.permission";
        }
    }

    private DataSource() {
        super();
    }

    public static RandomAccessFile openWriteableFile(String fileName) throws IOException {
        Properties p = new Properties();
        Reader r = new FileReader(Property.FILE_NAME);
        URI uri = URI.create(String.format("file://%s/%s", fileName));
        Path path = Paths.get(uri);

        p.load(r);
        path = path.toAbsolutePath();

        return new RandomAccessFile(path.toFile(), p.getProperty(Property.Permission.RW));
    }

    public static void close(RandomAccessFile file) throws FileSystemException {
        try {
            if (file != null)
                file.close();
        } catch (IOException cause) {
            throw new FileSystemException("PROBLEMAS AO FECHAR ARQUIVO", cause);
        }
    }
}
