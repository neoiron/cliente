package service.impl;

import repository.DAO;
import service.Service;
import domain.model.Entidade;

abstract class AbstractService<T extends Entidade<?, E>, E extends Throwable> implements Service<T, E> {

    protected final DAO<T, E> dao;

    protected AbstractService(DAO<T, E> dao) {
        super();

        this.dao = dao;
    }

    @Override
    public void validar(T domain) throws E {
        domain.validar();
    }

    @Override
    public void salvar(T domain) throws E {
        if (domain.isNullId()) {
            dao.inserir(domain);
        } else {
            dao.atualizar(domain);
        }
    }

    @Override
    public void apagar(T domain) throws E {
        dao.apagar(domain);
    }
}
