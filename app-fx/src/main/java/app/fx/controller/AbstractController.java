package app.fx.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import repository.jdbc.hsqldb.HsqlDBHelper;
import app.fx.api.Controller;
import app.fx.util.FXMLHelper;

@SuppressWarnings("deprecation")
abstract class AbstractController implements Controller {

    @FXML
    private Label statusLabel;

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

    @SuppressWarnings("unchecked")
    protected <V extends Parent> V getRoot() {
        return (V) getScene().getRoot();
    }

    protected void setStatus(String format, Object... values) {
        setStatus(String.format(format, values));
    }

    protected void setStatus(String message) {
        if (statusLabel == null)
            statusLabel = (Label) stage.getScene().lookup("#statusLabel");

        statusLabel.setText(message);
    }

    protected void clearStatus() {
        setStatus(null);
    }

    protected void done() {
        setStatus("Feito!");
    }

    protected void loadView(String viewTitle, String view, String... styles) throws Exception {
        BorderPane root = getRoot();
        Stage stage = getStage();
        Parent v = FXMLHelper.createView(stage, viewTitle, view, styles);

        root.setCenter(v);
        done();
    }

    protected void close() {
        stage.close();
    }

    protected void confirmAndClose(Event event) {
        Action a = Dialogs
                .create()
                .title(stage.getTitle())
                .masthead("Deseja sair?")
                .message("Caso sim, bye bye!")
                .showConfirm();

        if (Dialog.ACTION_YES.equals(a)) {
            HsqlDBHelper.stopServer();
            close();
        } else {
            event.consume();
        }
    }
}
