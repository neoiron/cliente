package repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.model.Entidade;
import repository.DAO;
import repository.exception.DatabaseException;

abstract class JdbcDAO<T extends Entidade<?, E>, E extends Throwable> implements DAO<T, E> {

    protected abstract E getFailInsert();
    protected abstract E getExceptionInsert();
    protected abstract String getSQLInsert();
    protected abstract void prepareStatementInsert(final PreparedStatement query, final T domain) throws SQLException;

    protected JdbcDAO() {
        super();

        DataSource.supportHsqlDB();
    }

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
    protected abstract void prepareStatementUpdate(PreparedStatement query, final T domain) throws SQLException;

    @Override
    public void atualizar(T domain) throws E {
        try (Connection c = DataSource.openConnection(); 
             PreparedStatement ps = c.prepareStatement(getSQLUpdate());) {

            prepareStatementUpdate(ps, domain);

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
    protected abstract void prepareStatementDelete(PreparedStatement query, final T domain) throws SQLException;

    @Override
    public void apagar(T domain) throws E {
        try (Connection c = DataSource.openConnection(); 
             PreparedStatement ps = c.prepareStatement(getSQLDelete());) {

            prepareStatementDelete(ps, domain);

            int rows = ps.executeUpdate();

            if (rows == 0)
                throw getFailDelete();
        } catch (SQLException | DatabaseException cause) {
            throw getExceptionDelete();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (DataSource.HSQL_SERVER != null) {
            DataSource.HSQL_SERVER.stop();
            DataSource.HSQL_SERVER.shutdown();
            DataSource.HSQL_SERVER = null;
            System.out.println("HSQLDB parado!");
        }

        super.finalize();
    }
}
