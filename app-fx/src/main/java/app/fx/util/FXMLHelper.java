package app.fx.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;

final public class FXMLHelper {

    private FXMLHelper() {
        super();
    }

    public static Parent createView(URL view, URL... styles) throws Exception {
        FXMLLoader fxml;
        Parent root;

        fxml = new FXMLLoader(view);
        root = fxml.load();

        for (URL style : styles) {
            root.getStylesheets().add(style.toExternalForm());
        }

        return root;
    }
}
