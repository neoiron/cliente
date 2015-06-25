package service;

import domain.Domain;

public interface Service<T extends Domain<?, ? extends Throwable>, E extends Throwable> {

    void validar(T domain) throws E;

    void salvar(T domain) throws E;

    void apagar(T domain) throws E;
}
