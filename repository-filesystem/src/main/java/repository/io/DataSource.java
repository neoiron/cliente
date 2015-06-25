package repository.io;

import repository.exception.FileSystemException;

import java.io.*;
import java.util.Properties;

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

    public static RandomAccessFile openFile() throws FileSystemException {
        Properties p;
        Reader r;
        try {
            p = new Properties();
            r = new FileReader(Property.FILE_NAME);

            p.load(r);

            return new RandomAccessFile(
                    p.getProperty(Property.SOURCE),
                    p.getProperty(Property.Permission.RW));
        } catch (FileNotFoundException cause) {
            throw new FileSystemException(
                    "ARQUIVO NÃO ENCONTRADO!",
                    cause);
        } catch (IOException cause) {
            throw new FileSystemException(
                    "PROBLEMAS AO LER ARQUIVO",
                    cause);
        }
    }

    public static void close(RandomAccessFile file) throws FileSystemException {
        try {
            if (file != null)
                file.close();
        } catch (IOException cause) {
            throw new FileSystemException(
                    "PROBLEMAS AO FECHAR ARQUIVO",
                    cause);
        }
    }
}
