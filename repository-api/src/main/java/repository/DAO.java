package repository;

interface DAO<T, E extends Throwable> {

    void inserir(T domain) throws E;

    void atualizar(T domain) throws E;

    void apagar(T domain) throws E;
}
