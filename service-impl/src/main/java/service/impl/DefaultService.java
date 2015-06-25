package service.impl;

import repository.DAO;
import service.Service;
import domain.model.Entidade;

abstract class DefaultService<T extends Entidade<?, E>, E extends Throwable> implements Service<T, E> {

    private DAO<T, E> dao;

    protected DefaultService(DAO<T, E> dao) {
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
