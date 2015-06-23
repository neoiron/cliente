package service;

import domain.model.Entidade;

interface Service<T extends Entidade<?>, E extends Exception> {

    void validar(T domain) throws E;

    void salvar(T domain) throws E;

    void apagar(T domain) throws E;
}
