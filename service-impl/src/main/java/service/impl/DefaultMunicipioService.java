package service.impl;

import domain.exception.MunicipioException;
import domain.model.Municipio;

public class DefaultMunicipioService extends AbstractService<Municipio, MunicipioException> {

    protected DefaultMunicipioService() {
        super(null);
    }
}
