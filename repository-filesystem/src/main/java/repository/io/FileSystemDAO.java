package repository.io;

import repository.DAO;
import domain.Domain;

abstract class FileSystemDAO<T extends Domain<E>, E extends Throwable> implements DAO<T, E> {

    @Override
    public void inserir(T domain) throws E {
        
    }
}
