package repository.jdbc.hsqldb;

public final class HsqlDBServer {

    public static void main(String[] args) {
        String command, database;

        if (args.length == 0)  {
            System.out.println("Informe:");
            System.out.printf("mvn exec:java <command> <database>");
            return;
        }

        command = args[0];

        switch (command.toLowerCase()) {
        case "start":
            database = args.length > 1 ? args[1] : "cliente";
            HsqlDBHelper.startServer(database);
            break;
        case "stop":
            HsqlDBHelper.stopServer();
            break;
        }
    }
}
