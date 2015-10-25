package facade.impl;

import domain.Domain;
import domain.model.Municipio;
import domain.model.UFVO;
import facade.api.Facade;
import service.MunicipioService;
import service.impl.DefaultMunicipioService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MunicipioFacade {

    private MunicipioService service = new DefaultMunicipioService();

    public static Map<CharSequence, Object> toMap(Domain model) throws Exception {
        Map<CharSequence, Object> map = new LinkedHashMap<>();
        Municipio m = (Municipio) model;

        map.put(Facade.Municipio.KEY_ID, model);
        map.put(Facade.Municipio.KEY_NOME, m.getNome());
        map.put(Facade.Municipio.KEY_UF, m.getUf());

        return map;
    }

    public void validar(Map<CharSequence, Object> map) throws Exception {
        final Municipio model = converter(map);

        service.validar(model);
    }

    public void salvar(Map<CharSequence, Object> map) throws Exception {
        final Municipio model = converter(map);

        service.salvar(model);
    }

    public void apagar(Map<CharSequence, Object> map) throws Exception {
        final Municipio model = converter(map);

        service.apagar(model);
    }

    public Collection<? extends Domain> listar(CharSequence uf) throws Exception {
        // TODO Criar serviço para listar todos os municípios.
        return service.listar(UFVO.valueOf(uf));
    }

    private Municipio converter(Map<CharSequence, Object> map) throws Exception {
        Object id = map.get(Facade.Municipio.KEY_ID);
        Municipio model;

        if (id == null) {
            model = new Municipio();
        } else {
            model = (Municipio) id;
        }

        model.setNome((CharSequence) map.get(Facade.Municipio.KEY_NOME));
        model.setUf((UFVO) map.get(Facade.Municipio.KEY_UF));

        return model;
    }
}
