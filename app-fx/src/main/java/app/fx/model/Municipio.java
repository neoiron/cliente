package app.fx.model;

import domain.Domain;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableStringValue;

public class Municipio {

    private ObservableObjectValue<Domain> domain;
    private ObservableStringValue nome;
    private ObservableObjectValue<Object> uf;

    public Municipio(Domain domain) {
        super();

        Object[] v = domain.toArray();
        this.domain = new SimpleObjectProperty<>((Domain) v[0]);
        this.nome = new SimpleStringProperty(v[1].toString());
        this.uf = new SimpleObjectProperty<>(v[2]);
    }

    public ObservableObjectValue<Domain> getDomain() {
        return domain;
    }

    public ObservableStringValue getNome() {
        return nome;
    }

    public ObservableObjectValue<Object> getUf() {
        return uf;
    }
}
