package service.impl;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;

public class DefaultMunicipioService extends AbstractService<Municipio, MunicipioException> {

    private MunicipioDAO dao;

    public DefaultMunicipioService() {
        this(null); // FIXME new DAO aqui.
    }

    protected DefaultMunicipioService(MunicipioDAO dao) {
        super(dao);

        this.dao = dao;
    }
}
