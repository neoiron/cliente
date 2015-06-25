package repository.io;

import repository.exception.FileSystemException;

import java.io.RandomAccessFile;

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
        return null;
    }
}
