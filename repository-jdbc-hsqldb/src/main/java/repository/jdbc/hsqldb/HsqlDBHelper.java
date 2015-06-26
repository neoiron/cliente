package repository.jdbc.hsqldb;

import org.hsqldb.Server;

public final class HsqlDBHelper {

    private static Server HSQL_SERVER = new Server();

    private HsqlDBHelper() {
        super();
    }

    public static void startServer(String database) {
        HSQL_SERVER.setLogWriter(null);
        HSQL_SERVER.setSilent(true);
        HSQL_SERVER.setDatabaseName(0, database);
        HSQL_SERVER.setDatabasePath(0, "file:target/".concat(database));

        HSQL_SERVER.start();
    }

    public static void stopServer() {
        if (HSQL_SERVER.isSilent()) {
            HSQL_SERVER.stop();
        }
    }
}
