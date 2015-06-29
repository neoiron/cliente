package service;

import java.util.Collection;
import java.util.stream.Collectors;

import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public interface MunicipioService extends Service<Municipio, MunicipioException> {

    Collection<Municipio> listar(UFVO uf) throws MunicipioException;

    default Collection<Municipio> listarInvertido(UFVO uf) throws MunicipioException {
        Collection<Municipio> municipios = listar(uf);

        return municipios.stream()
                .sorted((m1, m2) -> -m1.compareTo(m2))
                .collect(Collectors.toSet());
    }
}
