package repository.io;

import java.util.Set;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class FileMunicipioDAO implements MunicipioDAO {

    @Override
    public void inserir(Municipio domain) throws MunicipioException {
        
    }

    @Override
    public void atualizar(Municipio domain) throws MunicipioException {

    }

    @Override
    public void apagar(Municipio domain) throws MunicipioException {

    }

    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        return null;
    }
}
