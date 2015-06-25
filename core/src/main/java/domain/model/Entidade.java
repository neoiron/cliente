package domain.model;

import domain.Domain;

public abstract class Entidade<PK, E extends Throwable> implements Domain<PK, E> {

    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public boolean isNullId() {
        return id == null;
    }

    @Override
    public boolean isNotNullId() {
        return !isNullId();
    }

    @Override
    public String toString() {
        return String.format("id=%s", id);
    }
}
