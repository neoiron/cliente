package repository.io;

import java.io.IOException;
import java.io.RandomAccessFile;

import repository.DAO;
import domain.Domain;

abstract class FileSystemDAO<T extends Domain<E>, E extends Throwable> implements DAO<T, E> {

    public static final char CSV_SEPARATOR = ';';
    protected abstract String getFileName();
    protected abstract E getExceptionInsert(Throwable cause);

    @Override
    public void inserir(T domain) throws E {
        try (RandomAccessFile source = DataSource.openWriteableFile(getFileName())) {
            long pos = source.length() - 1;
            source.seek(pos);

            source.write(domain.toCSV(CSV_SEPARATOR).getBytes());
        } catch (IOException cause) {
            throw getExceptionInsert(cause);
        }
    }

    @Override
    public void atualizar(T domain) throws E {
        
    }

    @Override
    public void apagar(T domain) throws E {
        
    }
}
