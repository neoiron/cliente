package domain;

public interface Domain<PK, E extends Throwable> {

    PK getId();

    void setId(PK id);

    boolean isNullId();

    boolean isNotNullId();

    void validar() throws E;

    String toCSV(char separator);
}
