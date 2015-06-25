package repository.factory;

import repository.MunicipioDAO;
import repository.io.FileMunicipioDAO;

public final class FactoryDAO {

    private FactoryDAO() {
        super();
    }

    public static MunicipioDAO createMunicipioDAO() {
        return new FileMunicipioDAO();
    }

    // TODO Criar método createBairro()

    // TODO Criar método createLogradouro()

    // TODO Criar método createEndereco()

    // TODO Criar método createCliente()

    // TODO Criar método createContato()
}
