package domain.model;

import domain.exception.MunicipioException;

public class Municipio extends Entidade<Integer> implements Comparable<Municipio> {

    private CharSequence nome;
    private UFVO uf;

    public Municipio() {
        this("");
    }

    public Municipio(CharSequence nome) {
        this(nome, UFVO.SELECIONE);
    }

    public Municipio(CharSequence nome, CharSequence uf) {
        this(nome, UFVO.valueOf(uf));
    }

    public Municipio(CharSequence nome, UFVO uf) {
        super();

        this.nome = nome;
        this.uf = uf;
    }

    public CharSequence getNome() {
        return nome;
    }

    public void setNome(CharSequence nome) {
        this.nome = nome;
    }

    public UFVO getUf() {
        return uf;
    }

    public void setUf(CharSequence uf) {
        setUf(UFVO.valueOf(uf));
    }

    public void setUf(UFVO uf) {
        this.uf = uf;
    }

    public void validarNome() throws MunicipioException {
        if (nome == null) {
            throw new MunicipioException("Nome nulo do município!");
        }

        if (nome.length() == 0) {
            throw new MunicipioException("Por favor, informe o nome do município!");
        }

        if (!String.valueOf(nome).matches("[^a-zA-ZáéíóúàâêôãõüçÁÉÍÓÚÀÂÊÔÃÕÜÇ ]")) {
            throw new MunicipioException("Nome do município inválido!");
        }
    }

    public void validarUf() throws MunicipioException {
        if (uf == null) {
            throw new MunicipioException("UF nula do município!");
        }

        if (uf.isNotSelecionado()) {
            throw new MunicipioException("Por favor, selecione a UF do município!");
        }
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
