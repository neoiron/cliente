package repository.io;

abstract class FileDAO {

    public static final char CSV_SEPARATOR = ';';

    public static final String CSV_SPLIT_REGEX = String.valueOf(CSV_SEPARATOR);

    public static final long FILE_BEGIN = 0L;
    public static final long FILE_EMPTY = 0L;

    public static final char NEW_LINE = '\n';

    interface Fields {
        enum Municipio {
            ID, NOME, UF;
        }
        // TODO Bairro
        // TODO Logradouro
        // TODO Endereco
        // TODO Cliente
        // TODO Contato
    }
}
