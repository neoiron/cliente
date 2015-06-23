package service.impl;

import java.util.Collection;

import repository.MunicipioDAO;
import service.MunicipioService;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class DefaultMunicipioService extends AbstractService<Municipio, MunicipioException> implements MunicipioService {

    private MunicipioDAO dao;

    public DefaultMunicipioService() {
        this(null); // FIXME new DAO aqui.
    }

    protected DefaultMunicipioService(MunicipioDAO dao) {
        super(dao);

        this.dao = dao;
    }

    @Override
    public Collection<Municipio> listar(UFVO uf) throws MunicipioException {
        return dao.selecionar(uf);
    }
}
