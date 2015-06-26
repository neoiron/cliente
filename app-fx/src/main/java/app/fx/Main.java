package app.fx;

import repository.jdbc.hsqldb.HsqlDBHelper;
import app.fx.util.FXMLHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static app.fx.controller.MainController.VIEW_URL;
import static app.fx.controller.MainController.VIEW_TITLE;
import static app.fx.controller.MainController.STYLE_URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = FXMLHelper.createScene(
                primaryStage,
                VIEW_TITLE,
                VIEW_URL,
                STYLE_URL);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        HsqlDBHelper.startServer("cliente");
        launch(args);
    }
}
