package repository.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.hsqldb.Server;

import repository.exception.DatabaseException;

final class DataSource {

    interface Jdbc {
        String FILE_NAME = "conf/jdbc.properties";
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
        try (Reader r = new FileReader(Jdbc.FILE_NAME)) {
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

    public static Server HSQL_SERVER = null;

    public static void supportHsqlDB() {
        Properties p = new Properties();
        try (Reader r = new FileReader(Jdbc.FILE_NAME)) {
            p.load(r);

            String database = p.getProperty(Jdbc.DATABASE);

            if ("hsqldb".equalsIgnoreCase(database)) {
                HSQL_SERVER = new Server();

                HSQL_SERVER.setLogWriter(null);
                HSQL_SERVER.setSilent(true);
                HSQL_SERVER.setDatabaseName(0, "cliente");
                HSQL_SERVER.setDatabasePath(0, "file:target/clientedb");

                HSQL_SERVER.start();
            }
        } catch (Exception cause) {
            cause.printStackTrace();
        }
    }

    public static void createHsqlDBTable(String scriptDDL) {
        InputStream in = DataSource.class.getResourceAsStream(scriptDDL);

        createHsqlDBTable(in);
    }

    public static void createHsqlDBTable(InputStream scriptDDL) {
        try {
            if (HSQL_SERVER != null) {
                try (Connection c = DataSource.openConnection();
                     Statement query = c.createStatement();
                     BufferedReader ddl = new BufferedReader(new InputStreamReader(scriptDDL))) {
        
                    while (ddl.ready()) {
                        query.execute(ddl.readLine());
                    }
                }
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

    @Override
    protected void finalize() throws Throwable {
        if (HSQL_SERVER != null) {
            HSQL_SERVER.stop();
            HSQL_SERVER.shutdown();
            HSQL_SERVER = null;
        }

        super.finalize();
    }
}
