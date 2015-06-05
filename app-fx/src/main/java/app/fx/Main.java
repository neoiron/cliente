package app.fx;

import app.fx.util.FXMLHelper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL view, style;
        Parent root;
        Scene scene;

        view = FXMLHelper.class.getResource("/app/fx/view/main.fxml");
        style = this.getClass().getResource("/app/fx/view/main.css");
        root = FXMLHelper.createView(view);
        scene = new Scene(root);

        scene.getStylesheets().add(style.toExternalForm());

        primaryStage.setTitle("Cadastro de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
