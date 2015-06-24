package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.model.Entidade;
import repository.DAO;
import repository.exception.DatabaseException;

abstract class AbstractDAO<T extends Entidade<?, E>, E extends Throwable> implements DAO<T, E> {

    protected abstract E getFailInsert();
    protected abstract E getExceptionInsert();
    protected abstract String getSQLInsert();
    protected abstract void prepareStatementInsert(PreparedStatement query);

    @Override
    public void inserir(T domain) throws E {
        Connection c;
        PreparedStatement ps;

        try {
            c = DataSource.openConnection();
            ps = c.prepareStatement(getSQLInsert());

            try {
                prepareStatementInsert(ps);

                int rows = ps.executeUpdate();

                if (rows == 0) 
                    throw getFailInsert();
            } finally {
                DataSource.close(ps);
                DataSource.close(c);
            }
        } catch (SQLException | DatabaseException cause) {
            throw getExceptionInsert();
        }
    }

    protected abstract E getExceptionUpdate();
    protected abstract String getSQLUpdate();
    protected abstract void prepareStatementUpdate();

    @Override
    public void atualizar(T domain) throws E {
        
    }

    protected abstract E getExceptionDelete();
    protected abstract String getSQLDelete();
    protected abstract void prepareStatementDelete();

    @Override
    public void apagar(T domain) throws E {
        
    }
}
