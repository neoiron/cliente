package domain.model;

import domain.Domain;

public abstract class Entidade<PK, E extends Throwable> implements Domain<E> {

    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public boolean isNullId() {
        return id == null;
    }

    public boolean isNotNullId() {
        return !isNullId();
    }

    @Override
    public String toString() {
        return String.format("id=%s", id);
    }
}
