package app.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController extends AbstractController {

    public static final String STYLE_URL = "/app/fx/view/main.css";

    public static final String VIEW_URL = "/app/fx/view/main.fxml";
    public static final String VIEW_TITLE = "Cadastro de Clientes";

    @FXML
    public void onClickClienteListarMenu(ActionEvent event) {
        System.out.println("Menu cliente > listar funcionando!");
    }

    @FXML
    public void onClickLogradouroMenu(ActionEvent event) {
        System.out.println("Menu logradouro funcionando!");
    }

    @FXML
    public void onClickBairroMenu(ActionEvent event) {
        System.out.println("Menu bairro funcionando!");
    }

    @FXML
    public void onClickMunicipioMenu(ActionEvent event) {
        System.out.println("Menu município funcionando!");
    }

    @FXML
    public void onClickFecharMenu(ActionEvent event) {
        System.out.println("Menu fechar funcionando!");
    }
}
