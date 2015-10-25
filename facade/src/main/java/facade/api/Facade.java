package facade.api;

public interface Facade {

    interface UF {
        String KEY_FACADE = "uf_facade";
        String KEY_ID = "uf_id";
        String KEY_IDS = "uf_ids";
    }

    interface Municipio {
        String KEY_FACADE = "municipio_facade";
        String KEY_ID = "municipio_id";
        String KEY_NOME = "municipio_nome";
        String KEY_UF = "municipio_uf";
    }
}
