package repository;

import domain.model.Entidade;

public interface DAO<T extends Entidade<?>, E extends Throwable> {

    void inserir(T domain) throws E;

    void atualizar(T domain) throws E;

    void apagar(T domain) throws E;
}
