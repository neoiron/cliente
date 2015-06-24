package repository.factory;

import repository.DAO;
import domain.model.Entidade;

abstract class FactoryDAO {

    protected FactoryDAO() {
        super();
    }

    protected static <T extends Entidade<?, E>, E extends Throwable> DAO<T, E> newInstance(Class<DAO<T, E>> _class) 
            throws InstantiationException, IllegalAccessException {
        return _class.newInstance();
    }
}
