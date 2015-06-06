package app.fx.controller;

import app.fx.api.Controller;
import app.fx.util.FXMLHelper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

abstract class AbstractController implements Controller {

    private Stage stage;

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected Scene getScene() {
        return stage.getScene();
    }

    protected <V extends Parent> V getRoot() {
        return (V) getScene().getRoot();
    }

    protected void loadView(String viewTitle, String view, String... styles) throws Exception {
        BorderPane root = getRoot();
        Stage stage = getStage();
        Parent v = FXMLHelper.createView(stage, viewTitle, view, styles);

        root.setCenter(v);
    }
}
