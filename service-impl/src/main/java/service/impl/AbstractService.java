package service.impl;

import repository.DAO;
import domain.model.Entidade;

abstract class AbstractService<T extends Entidade<?>, E extends Exception> {

    protected final DAO<T, E> dao;

    public AbstractService(DAO<T, E> dao) {
        super();

        this.dao = dao;
    }
}
