package repository.jdbc;

import java.sql.PreparedStatement;

import domain.exception.MunicipioException;
import domain.model.Municipio;

public class JdbcMunicipioDAO extends AbstractDAO<Municipio, MunicipioException> {

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
}
