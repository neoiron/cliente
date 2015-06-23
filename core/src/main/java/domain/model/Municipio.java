package domain.model;

public class Municipio implements Comparable<Municipio> {

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
    public int compareTo(Municipio o) {
        int comp = uf.compareTo(o.uf);

        if (comp == 0) {
            comp = String.valueOf(nome).compareToIgnoreCase(String.valueOf(o.nome));
        }

        return comp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) obj;
        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (uf != other.uf) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Municipio m = new Municipio();

        m.setNome(nome);
        m.setUf(uf);

        return m;
    }

    @Override
    protected void finalize() throws Throwable {
        nome = null;
        uf = null;

        super.finalize();
    }

    @Override
    public String toString() {
        return String.format("Municipio [nome=%s, uf=%s, %s]", nome, uf, super.toString());
    }
}
