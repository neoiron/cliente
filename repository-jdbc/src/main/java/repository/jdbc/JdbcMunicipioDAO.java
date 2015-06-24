package repository.jdbc;

import java.sql.PreparedStatement;
import java.util.Set;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class JdbcMunicipioDAO extends AbstractDAO<Municipio, MunicipioException> implements MunicipioDAO {

    @Override
    protected MunicipioException getFailInsert() {
        return null;
    }

    @Override
    protected MunicipioException getExceptionInsert() {
        return null;
    }

    @Override
    protected String getSQLInsert() {
        return null;
    }

    @Override
    protected void prepareStatementInsert(PreparedStatement query) {
        
    }

    @Override
    protected MunicipioException getFailUpdate() {
        return null;
    }

    @Override
    protected MunicipioException getExceptionUpdate() {
        return null;
    }

    @Override
    protected String getSQLUpdate() {
        return null;
    }

    @Override
    protected void prepareStatementUpdate(PreparedStatement query) {
        
    }

    @Override
    protected MunicipioException getFailDelete() {
        return null;
    }

    @Override
    protected MunicipioException getExceptionDelete() {
        return null;
    }

    @Override
    protected String getSQLDelete() {
        return null;
    }

    @Override
    protected void prepareStatementDelete(PreparedStatement ps) {
        
    }

    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        return null;
    }
}
