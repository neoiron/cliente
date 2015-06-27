package repository.jdbc.hsqldb;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.hsqldb.Server;

public final class HsqlDBHelper {

    private static Server HSQL_SERVER = new Server();

    private static Path FILE_PROPERTIES = Paths.get("conf/jdbc.properties");
    private static String KEY_DATABASE = "jdbc.database";

    private HsqlDBHelper() {
        super();
    }

    public static void startServer(String database) {
        if (isHsqlDB()) {
            HSQL_SERVER.setLogWriter(null);
            HSQL_SERVER.setSilent(true);
            HSQL_SERVER.setDatabaseName(0, database);
            HSQL_SERVER.setDatabasePath(0, "file:target/".concat(database));

            HSQL_SERVER.start();
        }
    }

    public static boolean isHsqlDB() {
        Properties p = new Properties();
        String database = null;

        try (Reader r = new FileReader(FILE_PROPERTIES.toFile())) {
            p.load(r);

            database = p.getProperty(KEY_DATABASE);
        } catch (Exception cause) {
            cause.printStackTrace();
        }

        return "hsqldb".equalsIgnoreCase(database);
    }

    public static void stopServer() {
        if (isHsqlDB()) {
            HSQL_SERVER.stop();
            HSQL_SERVER.shutdown();
        }
    }
}
