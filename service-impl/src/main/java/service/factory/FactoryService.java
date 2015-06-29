package service.factory;

import service.MunicipioService;
import service.impl.DefaultMunicipioService;

public final class FactoryService {

    private FactoryService() {
        super();
    }

    public static MunicipioService createMunicipioService() {
        return new DefaultMunicipioService();
    }
}
