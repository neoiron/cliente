package facade.impl;

import domain.model.Municipio;
import service.MunicipioService;
import service.impl.DefaultMunicipioService;

public class MunicipioFacade {

    private MunicipioService service = new DefaultMunicipioService();

    public void validar(CharSequence municipio, CharSequence uf) throws Exception {
        validar(null, municipio, uf);
    }

    public void validar(CharSequence id, CharSequence municipio, CharSequence uf) throws Exception {
        Municipio domain = new Municipio(municipio, uf);

        domain.setId(id == null || "".equals(id) ? null : Integer.valueOf(id.toString()));

        validar(domain);
    }

    public void validar(Object municipio) throws Exception {
        service.validar((Municipio) municipio);
    }

    public void salvar(CharSequence municipio, CharSequence uf) throws Exception {
        salvar(null, municipio, uf);
    }

    public void salvar(CharSequence id, CharSequence municipio, CharSequence uf) throws Exception {
        Municipio domain = new Municipio(municipio, uf);

        domain.setId(id == null || "".equals(id) ? null : Integer.valueOf(id.toString()));

        salvar(domain);
    }

    public void salvar(Object municipio) throws Exception {
        service.salvar((Municipio) municipio);
    }

    public void apagar(Object municipio) throws Exception {
        service.apagar((Municipio) municipio);
    }
}
