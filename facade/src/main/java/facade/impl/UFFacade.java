package facade.impl;

import domain.model.UFVO;

import java.util.Arrays;
import java.util.Collection;

public class UFFacade {

    public static Object valueOf(CharSequence uf) {
        return UFVO.valueOf(uf);
    }

    public Collection<Object> listarUFs() {
        return Arrays.asList(UFVO.values());
    }

    public Object getSelecione() {
        return UFVO.SELECIONE;
    }

}
