package facade.impl;

import domain.model.UFVO;

import java.util.Arrays;
import java.util.Collection;

public class UFFacade {

    public interface Fields {
        String UF = "uf";
        String UFS = "ufs";
    }

    public Collection<UFVO> listarUFs() {
        return Arrays.asList(UFVO.values());
    }

    public static UFVO valueOf(CharSequence uf) {
        return UFVO.valueOf(uf);
    }
}
