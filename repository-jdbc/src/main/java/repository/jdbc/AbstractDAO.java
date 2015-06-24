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
    protected abstract void prepareStatementInsert(final PreparedStatement query, final T domain) throws SQLException;

    @Override
    public void inserir(T domain) throws E {
        Connection c;
        PreparedStatement ps;

        try {
            c = DataSource.openConnection();
            ps = c.prepareStatement(getSQLInsert());

            try {
                prepareStatementInsert(ps, domain);

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

    protected abstract E getFailUpdate();
    protected abstract E getExceptionUpdate();
    protected abstract String getSQLUpdate();
    protected abstract void prepareStatementUpdate(PreparedStatement query);

    @Override
    public void atualizar(T domain) throws E {
        try (Connection c = DataSource.openConnection(); 
             PreparedStatement ps = c.prepareStatement(getSQLUpdate());) {

            prepareStatementUpdate(ps);

            int rows = ps.executeUpdate();

            if (rows == 0)
                throw getFailUpdate();
        } catch (SQLException | DatabaseException cause) {
            throw getExceptionUpdate();
        }
    }

    protected abstract E getFailDelete();
    protected abstract E getExceptionDelete();
    protected abstract String getSQLDelete();
    protected abstract void prepareStatementDelete(PreparedStatement ps);

    @Override
    public void apagar(T domain) throws E {
        try (Connection c = DataSource.openConnection(); 
             PreparedStatement ps = c.prepareStatement(getSQLDelete());) {

            prepareStatementDelete(ps);

            int rows = ps.executeUpdate();

            if (rows == 0)
                throw getFailDelete();
        } catch (SQLException | DatabaseException cause) {
            throw getExceptionDelete();
        }
    }
}
