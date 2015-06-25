package app.fx.model;

import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;
import domain.model.UFVO;

public class Municipio {

    private ObservableObjectValue<domain.model.Municipio> domain;
    private ObservableStringValue nome;
    private ObservableObjectValue<UFVO> uf;
}
