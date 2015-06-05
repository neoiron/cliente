package app.fx.controller;

import app.fx.api.Controller;
import javafx.stage.Stage;

public class MainController implements Controller {

    private Stage stage;

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
