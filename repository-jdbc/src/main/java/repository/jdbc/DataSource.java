package repository.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import repository.exception.DatabaseException;

final class DataSource {

    interface Jdbc {
        Path FILE_NAME = Paths.get("conf/jdbc.properties").toAbsolutePath();
        String DATABASE = "jdbc.database";
        String DRIVER = "jdbc.driver";
        String URL = "jdbc.url";
        String USER = "jdbc.user";
        String PASSWORD = "jdbc.password";
    }

    private DataSource() {
        super();
    }

    public static Connection openConnection() throws DatabaseException {
        Properties p = new Properties();
        try (Reader r = new FileReader(Jdbc.FILE_NAME.toFile())) {
            p.load(r);

            Class.forName(p.getProperty(Jdbc.DRIVER));

            return DriverManager.getConnection(p.getProperty(Jdbc.URL), p.getProperty(Jdbc.USER),
                    p.getProperty(Jdbc.PASSWORD));
        } catch (SQLException cause) {
            throw new DatabaseException("PROBLEMAS AO ABRIR CONEXÃO COM BANCO DE DADOS!", cause);
        } catch (IOException cause) {
            throw new DatabaseException("PROBLEMAS AO ABRIR 'jdbc.properties'!", cause);
        } catch (ClassNotFoundException cause) {
            throw new DatabaseException("PROBLEMAS AO CARREGAR O DRIVER!", cause);
        }
    }

    public static void createHsqlDBTable(String scriptDDL) {
        InputStream in = DataSource.class.getResourceAsStream(scriptDDL);

        createHsqlDBTable(in);
    }

    public static void createHsqlDBTable(InputStream scriptDDL) {
        try (Connection c = DataSource.openConnection();
             Statement query = c.createStatement();
             BufferedReader ddl = new BufferedReader(new InputStreamReader(scriptDDL))) {

            while (ddl.ready()) {
                query.execute(ddl.readLine());
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        }
    }

    public static void close(Statement statement) throws DatabaseException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException cause) {
            throw new DatabaseException("PROBLEMAS AO FECHAR CONSULTA!", cause);
        }
    }

    public static void close(Connection connection) throws DatabaseException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException cause) {
            throw new DatabaseException("PROBLEMAS AO FECHAR CONEXÃO COM BANCO DE DADOS!", cause);
        }
    }
}
