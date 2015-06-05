package app.fx.util;

import app.fx.api.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

final public class FXMLHelper {

    private FXMLHelper() {
        super();
    }

    public static Scene createScene(Stage stage, String view, String... styles) throws Exception {
        URL v;
        URL[] s;

        v = FXMLHelper.class.getResource(view);
        s = new URL[styles.length];

        for (int i = 0; i < styles.length; i++) {
            s[i] = FXMLHelper.class.getResource(styles[i]);
        }

        return createScene(stage, v, s);
    }

    public static Scene createScene(Stage stage, URL view, URL... styles) throws Exception {
        Parent root = createView(stage, view, styles);

        return new Scene(root);
    }

    public static Parent createView(Stage stage, URL view, URL... styles) throws Exception {
        FXMLLoader fxml;
        Controller controller;
        Parent root;

        fxml = new FXMLLoader(view);
        root = fxml.load();
        controller = fxml.getController();

        for (URL style : styles) {
            root.getStylesheets().add(style.toExternalForm());
        }

        controller.setStage(stage);

        return root;
    }
}
