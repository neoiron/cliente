package app.fx.controller;

import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import service.MunicipioService;
import service.factory.FactoryService;
import domain.exception.MunicipioInvalidoException;
import domain.model.Municipio;
import domain.model.UFVO;

@SuppressWarnings("deprecation")
public class MunicipioController extends AbstractController {

    public static final String STYLE_URL = "/app/fx/view/municipio.css";

    public static final String VIEW_URL = "/app/fx/view/municipio.fxml";
    public static final String VIEW_TITLE = "Cadastro de Municípios";

    private MunicipioService service;

    private Municipio aalterar = null;

    @FXML
    private ComboBox<UFVO> cbUF;

    @FXML
    private TextField tfNOME;

    @FXML
    private TableView<app.fx.model.Municipio> tvMUNICIPIOS;

    private ContextMenu cm;

    @FXML
    private TableColumn<app.fx.model.Municipio, String> tcNOME;

    @FXML
    private TableColumn<app.fx.model.Municipio, UFVO> tcUF;

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
        mi.setOnAction(this::onUpdateAction);

        mi = cm.getItems().get(1);
        mi.setOnAction(this::onDeleteAction);

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
        tvMUNICIPIOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cbUF.getItems().addAll(UFVO.values());
        cbUF.setValue(UFVO.SELECIONE);

        tcNOME.setCellValueFactory(data -> data.getValue().getNome());
        tcUF.setCellValueFactory(data -> data.getValue().getUf());
    }

    @FXML
    private void onChangeUFAction(ActionEvent e) {
        try {
            loadTable();
            tfNOME.requestFocus();
            tfNOME.selectAll();
        } catch (Exception cause) {
            Dialogs
                .create()
                .title(getStage().getTitle())
                .masthead(cause.getMessage())
                .message("OPS...")
                .showException(cause);
        }
    }

    @FXML
    private void onSaveAction(ActionEvent e) {
        Municipio domain;
        try {
            if (aalterar == null) {
                domain = new Municipio();
            } else {
                domain = aalterar;
                aalterar = null;
            }

            domain.setNome(tfNOME.getText());
            domain.setUf(cbUF.getValue());

            service.validar(domain);
            service.salvar(domain);
            clearForm();
            loadTable();
        } catch (MunicipioInvalidoException cause) {
            tfNOME.requestFocus();
            tfNOME.selectAll();
            Dialogs
                .create()
                .title(getStage().getTitle())
                .masthead("Falha na validação...")
                .message(cause.getMessage())
                .showWarning();
        } catch (Exception cause) {
            Dialogs
                .create()
                .title(getStage().getTitle())
                .masthead(cause.getMessage())
                .message("OPS...")
                .showException(cause);
        }
    }

    @FXML
    private void onUpdateAction(ActionEvent e) {
        TableViewSelectionModel<app.fx.model.Municipio> model;
        ObservableList<Integer> indices;
        int size;

        try {
            model = tvMUNICIPIOS.getSelectionModel();
            indices = model.getSelectedIndices();
            size = indices.size();

            switch (size) {
            case 0:
                Dialogs
                    .create()
                    .title(getStage().getTitle())
                    .masthead("Apagando...")
                    .message("Favor, selecione pelo menos 1 município para apagar!")
                    .showWarning();
                break;
            case 1:
                app.fx.model.Municipio m = model.getSelectedItem();

                aalterar = m.getDomain().get();
                tfNOME.setText(m.getNome().get());
                tfNOME.requestFocus();
                tfNOME.selectAll();
                break;
            }
        } catch (Exception cause) {
            Dialogs
                .create()
                .title(getStage().getTitle())
                .masthead(cause.getMessage())
                .message("OPS...")
                .showException(cause);
        }
    }

    @FXML
    private void onDeleteAction(ActionEvent e) {
        TableViewSelectionModel<app.fx.model.Municipio> model;
        ObservableList<Integer> indices;
        int size;

        try {
            model = tvMUNICIPIOS.getSelectionModel();
            indices = model.getSelectedIndices();
            size = indices.size();

            switch (size) {
            case 0:
                Dialogs
                    .create()
                    .title(getStage().getTitle())
                    .masthead("Apagando...")
                    .message("Favor, selecione pelo menos 1 município para apagar!")
                    .showWarning();
                break;
            default:
                String message;
                app.fx.model.Municipio m;

                if (size == 1) {
                    m = model.getSelectedItem();
                    message = String.format("%s / %s", m.getNome().get(), m.getUf().get());
                } else {
                    message = String.format("%s municípios", size);
                }

                Action a = Dialogs
                                .create()
                                .title(getStage().getTitle())
                                .masthead(message)
                                .message("Confirma apagar?")
                                .showConfirm();

                if (Dialog.ACTION_YES.equals(a)) {
                    for (app.fx.model.Municipio i : model.getSelectedItems()) {
                        service.apagar(i.getDomain().get());
                    }

                    loadTable();
                } else {
                    model.clearSelection();
                }
                break;
            }
        } catch (Exception cause) {
            Dialogs
                .create()
                .title(getStage().getTitle())
                .masthead(cause.getMessage())
                .message("OPS...")
                .showException(cause);
        }
    }

    @FXML
    private void onCancelAction(ActionEvent e) {
        clearForm();
    }

    private void loadTable() throws Exception {
        ObservableList<app.fx.model.Municipio> items;
        Collection<Municipio> municipios;
        UFVO uf;

        items = tvMUNICIPIOS.getItems();
        uf = cbUF.getValue();
        municipios = service.listar(uf);

        items.clear();
        municipios.forEach(m -> items.add(new app.fx.model.Municipio(m)));
        setStatus("%s município(s)", municipios.size());
    }

    private void clearForm() {
        tfNOME.setText(null);
        tfNOME.requestFocus();
    }
}
