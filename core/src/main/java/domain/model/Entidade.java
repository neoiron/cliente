package domain.model;

public class Entidade<PK> {

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
