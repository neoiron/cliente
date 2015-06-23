package domain.model;

public class Entidade<PK> {

    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id=%s", id);
    }
}
