package domain;

public interface Domain<E extends Exception> {

    void validar() throws E;
}
