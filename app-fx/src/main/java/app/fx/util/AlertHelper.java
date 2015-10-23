package app.fx.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public final class AlertHelper {

    private static Stage stage;
    private static Alert alert;

    private AlertHelper() {
        super();
    }

    public static AlertHelper createConfirmation(Stage stage) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        return create(stage);
    }

    public static AlertHelper createConfirmation(Stage stage, String headerText) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(headerText);
        return create(stage);
    }

    public static AlertHelper createInformation(Stage stage) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        return create(stage);
    }

    public static AlertHelper createWarning(Stage stage) {
        alert = new Alert(Alert.AlertType.WARNING);
        return create(stage);
    }

    public static AlertHelper createWarning(Stage stage, Throwable cause, String headerText) {
        alert = new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText(headerText);

        if (cause != null) {
            cause.printStackTrace();
            alert.setContentText(cause.getMessage());
        }

        return create(stage);
    }

    public static AlertHelper createError(Stage stage) {
        return createError(stage, null);
    }

    public static AlertHelper createError(Stage stage, Throwable cause) {
        alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("OPS...");

        if (cause != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            cause.printStackTrace();
            cause.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("Stacktrace:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.setContentText(cause.getMessage());
            alert.getDialogPane().setExpandableContent(expContent);
        }

        return create(stage);
    }

    private static AlertHelper create(Stage _stage) {
        stage = _stage;
        alert.setTitle(stage.getTitle());
        return new AlertHelper();
    }

    public AlertHelper setTitle(String text) {
        alert.setTitle(text);
        return this;
    }

    public AlertHelper setHeaderText(String text) {
        alert.setHeaderText(text);
        return this;
    }

    public AlertHelper setContentText(String text) {
        alert.setContentText(text);
        return this;
    }

    public boolean showAndWait(String text) {
        setContentText(text);
        return ButtonType.OK == showAndWait().get();
    }

    public Optional<ButtonType> showAndWait() {
        return alert.showAndWait();
    }

    public Alert getAlert() {
        return alert;
    }
}
