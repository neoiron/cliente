package repository.jdbc;

import java.sql.Connection;

import repository.exception.DatabaseException;

final class DataSource {

    private DataSource() {
        super();
    }

    public static Connection openConnection() throws DatabaseException {
        return null;
    }

    public static void close(Connection connection) throws DatabaseException {
        
    }
}
