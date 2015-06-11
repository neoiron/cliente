package app.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;

public class MunicipioController extends AbstractController {

    public static final String STYLE_URL = "/app/fx/view/municipio.css";

    public static final String VIEW_URL = "/app/fx/view/municipio.fxml";
    public static final String VIEW_TITLE = "Cadastro de Municípios";

    @FXML
    private TableView<?> tvMUNICIPIOS;

    private ContextMenu cm;

    @FXML
    public void initialize() {
        initView();
        initActions();
    }

    private void initActions() {
        MenuItem mi;

        mi = cm.getItems().get(0);
        mi.setOnAction(e -> System.out.println("Menu 'atualizar' municipio funcionando!"));

        mi = cm.getItems().get(1);
        mi.setOnAction(e -> System.out.println("Menu 'apagar' municipio funcionando!"));
    }

    private void initView() {
        MenuItem mi;

        cm = new ContextMenu();
        mi = new MenuItem("Atualizar");
        cm.getItems().add(0, mi);
        mi = new MenuItem("Apagar");
        cm.getItems().add(1, mi);
        cm.getItems().add(2, new SeparatorMenuItem());
        mi = new MenuItem("Selecionar todos");
        cm.getItems().add(3, mi);

        tvMUNICIPIOS.setContextMenu(cm);
    }
}
