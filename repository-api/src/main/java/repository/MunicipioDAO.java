package repository;

import java.util.Set;

import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public interface MunicipioDAO extends DAO<Municipio, MunicipioException> {

    Set<Municipio> selecionar(UFVO uf) throws MunicipioException;
}
