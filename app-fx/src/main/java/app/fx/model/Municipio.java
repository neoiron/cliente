package app.fx.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;
import domain.model.UFVO;

public class Municipio {

    private ObservableObjectValue<domain.model.Municipio> domain;
    private ObservableStringValue nome;
    private ObservableObjectValue<UFVO> uf;

    public Municipio(domain.model.Municipio domain) {
        super();

        this.domain = new SimpleObjectProperty<>(domain);
        this.nome = new SimpleStringProperty(String.valueOf(domain.getNome()));
        this.uf = new SimpleObjectProperty<>(domain.getUf());
    }
}
