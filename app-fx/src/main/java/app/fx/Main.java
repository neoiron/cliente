package app.fx;

import app.fx.util.FXMLHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = FXMLHelper.createScene(
                primaryStage,
                "/app/fx/view/main.fxml",
                "/app/fx/view/main.css");

        primaryStage.setTitle("Cadastro de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
