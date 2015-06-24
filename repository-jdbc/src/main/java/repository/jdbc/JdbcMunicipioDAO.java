package repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class JdbcMunicipioDAO extends AbstractDAO<Municipio, MunicipioException> implements MunicipioDAO {

    @Override
    protected MunicipioException getFailInsert() {
        return new MunicipioException("Deveria inserir e não inseriu!");
    }

    @Override
    protected MunicipioException getExceptionInsert() {
        return new MunicipioException("PROBLEMAS AO INSERIR MUNICÍPIO NO BANCO DE DADOS!");
    }

    @Override
    protected String getSQLInsert() {
        return "INSERT INTO municipio (nm_municipio, id_uf) VALUES(?, ?)";
    }

    @Override
    protected void prepareStatementInsert(PreparedStatement query, Municipio domain) throws SQLException {
        query.setString(1, domain.getNome().toString());
        query.setString(2, domain.getUf().toString());
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
