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
        String FILE_NAME = "conf/io.properties";
        String SOURCE = "io.source";

        interface Permission {
            String RW = "rw";
        }
    }

    private DataSource() {
        super();
    }

    public static RandomAccessFile openWriteableFile(String fileName) throws IOException {
        Properties p = new Properties();
        Reader r = new FileReader(Property.FILE_NAME);
        URI uri;
        Path path;

        p.load(r);

        uri = URI.create(String.format("file://%s/%s", p.getProperty(Property.SOURCE), fileName));
        path = Paths.get(uri);
        path = path.toAbsolutePath();

        return new RandomAccessFile(path.toFile(), Property.Permission.RW);
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
