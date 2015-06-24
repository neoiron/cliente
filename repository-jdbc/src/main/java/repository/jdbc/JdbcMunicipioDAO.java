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
        return new MunicipioException("Deveria atualizar e não atualizou!");
    }

    @Override
    protected MunicipioException getExceptionUpdate() {
        return new MunicipioException("PROBLEMAS AO ATUALIZAR MUNICÍPIO NO BANCO DE DADOS!");
    }

    @Override
    protected String getSQLUpdate() {
        return "UPDATE municipio SET nm_municipio = ?, id_uf = ? WHERE id_municipio = ?";
    }

    @Override
    protected void prepareStatementUpdate(PreparedStatement query, Municipio domain) throws SQLException {
        query.setString(1, domain.getNome().toString());
        query.setString(2, domain.getUf().toString());
        query.setInt(3, domain.getId());
    }

    @Override
    protected MunicipioException getFailDelete() {
        return new MunicipioException("Deveria apagar e não apagou!");
    }

    @Override
    protected MunicipioException getExceptionDelete() {
        return new MunicipioException("PROBLEMAS AO APAGAR MUNICÍPIO NO BANCO DE DADOS!");
    }

    @Override
    protected String getSQLDelete() {
        return "DELETE FROM municipio WHERE id_municipio = ?";
    }

    @Override
    protected void prepareStatementDelete(PreparedStatement query, Municipio domain) throws SQLException {
        query.setInt(1, domain.getId());
    }

    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        return null;
    }
}
