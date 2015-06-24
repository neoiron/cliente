package repository.jdbc;

import domain.model.Entidade;
import repository.DAO;

abstract class AbstractDAO<T extends Entidade<?, E>, E extends Throwable> implements DAO<T, E> {

}
