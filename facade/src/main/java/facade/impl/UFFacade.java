package facade.impl;

import domain.model.UFVO;

import java.util.Arrays;
import java.util.Collection;

public class UFFacade {

    public Collection<UFVO> listarUFs() {
        return Arrays.asList(UFVO.values());
    }
}
