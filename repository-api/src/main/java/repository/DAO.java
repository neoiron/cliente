package repository;

import domain.Domain;

public interface DAO<T extends Domain<?, ? extends Throwable>, E extends Throwable> {

    void inserir(T domain) throws E;

    void atualizar(T domain) throws E;

    void apagar(T domain) throws E;
}
