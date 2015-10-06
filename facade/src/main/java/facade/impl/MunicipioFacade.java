package facade.impl;

import domain.model.Municipio;
import service.MunicipioService;
import service.impl.DefaultMunicipioService;

public class MunicipioFacade {

    private MunicipioService service = new DefaultMunicipioService();

    public void validar(Object municipio) throws Exception {
        service.validar((Municipio) municipio);
    }
}
