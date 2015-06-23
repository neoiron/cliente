package domain.model;

public class Municipio {

    private CharSequence nome;
    private UFVO uf;

    public CharSequence getNome() {
        return nome;
    }

    public void setNome(CharSequence nome) {
        this.nome = nome;
    }

    public UFVO getUf() {
        return uf;
    }

    public void setUf(UFVO uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return String.format("Municipio [nome=%s, uf=%s, %s]", nome, uf, super.toString());
    }
}
