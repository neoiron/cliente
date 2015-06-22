package service;

import java.util.Collection;

import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public interface MunicipioService extends Service<Municipio, MunicipioException> {

    Collection<Municipio> listar(UFVO uf) throws MunicipioException;
}
