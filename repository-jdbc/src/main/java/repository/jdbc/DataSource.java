package repository.jdbc;

import java.sql.Connection;

import repository.exception.DatabaseException;

final class DataSource {

    interface JDBC {
        String FILE_NAME = "jdbc.properties";
        String DRIVER = "jdbc.driver";
        String URL = "jdbc.driver";
        String USER = "jdbc.user";
        String PASSWORD = "jdbc.password";
    }

    private DataSource() {
        super();
    }

    public static Connection openConnection() throws DatabaseException {
        return null;
    }

    public static void close(Connection connection) throws DatabaseException {
        
    }
}
