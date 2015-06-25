package domain;

public interface Domain<E extends Throwable> {

    void validar() throws E;

    String toCSV(char separator);
}
