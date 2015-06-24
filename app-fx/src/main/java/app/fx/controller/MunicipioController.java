package app.fx.controller;

import service.MunicipioService;
import service.factory.FactoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import domain.model.UFVO;

public class MunicipioController extends AbstractController {

    public static final String STYLE_URL = "/app/fx/view/municipio.css";

    public static final String VIEW_URL = "/app/fx/view/municipio.fxml";
    public static final String VIEW_TITLE = "Cadastro de Municípios";

    private MunicipioService service;

    @FXML
    private ComboBox<UFVO> cbUFS;

    @FXML
    private TableView<?> tvMUNICIPIOS;

    private ContextMenu cm;

    @FXML
    public void initialize() {
        initView();
        initActions();
        initServices();
    }

    private void initServices() {
        service = FactoryService.createMunicipioService();
    }

    private void initActions() {
        MenuItem mi;

        mi = cm.getItems().get(0);
        mi.setOnAction(e -> System.out.println("Menu 'atualizar' municipio funcionando!"));

        mi = cm.getItems().get(1);
        mi.setOnAction(e -> System.out.println("Menu 'apagar' municipio funcionando!"));

        mi = cm.getItems().get(3);  
        mi.setOnAction(e -> System.out.println("Menu 'selecionar todos' municipio funcionando!"));
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

        cbUFS.getItems().addAll(UFVO.values());
        cbUFS.setValue(UFVO.SELECIONE);
    }

    @FXML
    private void onChangeUFAction(ActionEvent e) {
        System.out.println("ComboBox 'uf' municipio funcionando!");
    }

    @FXML
    private void onSaveAction(ActionEvent e) {
        System.out.println("Button 'ok' municipio funcionando!");
    }

    @FXML
    private void onCancelAction(ActionEvent e) {
        System.out.println("Button 'cancelar' municipio funcionando!");
    }
}
