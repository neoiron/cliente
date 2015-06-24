package repository.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        Properties p = new Properties();
        try (Reader r = new FileReader(JDBC.FILE_NAME)) {
            p.load(r);

            Class.forName(p.getProperty(JDBC.DRIVER));

            return DriverManager.getConnection(
                    p.getProperty(JDBC.URL), 
                    p.getProperty(JDBC.USER),
                    p.getProperty(JDBC.PASSWORD));
        } catch (SQLException cause) {
            throw new DatabaseException(
                    "PROBLEMAS AO ABRIR CONEXÃO COM BANCO DE DADOS!", 
                    cause);
        } catch (IOException cause) {
            throw new DatabaseException(
                    "PROBLEMAS AO ABRIR 'jdbc.properties'!", 
                    cause);
        } catch (ClassNotFoundException cause) {
            throw new DatabaseException(
                    "PROBLEMAS AO CARREGAR O DRIVER!", 
                    cause);
        }
    }

    public static void close(Connection connection) throws DatabaseException {
        
    }
}
