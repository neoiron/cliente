package repository.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class FileMunicipioDAO extends FileDAO implements MunicipioDAO {

    public static final String FILE_NAME = "municipio.txt";

    @Override
    public void inserir(Municipio domain) throws MunicipioException {
        try (RandomAccessFile source = DataSource.openWriteableFile(FILE_NAME)) {
            long size = source.length();
            int line = 1;

            if (size > 0) {
                Municipio found;
                String[] fields;
                long pos;

                source.seek(FILE_BEGIN);

                do {
                    fields = source.readLine().split(String.valueOf(CSV_SEPARATOR));
                    found = new Municipio(fields[1], fields[2]);

                    if (found.equals(domain)) {
                        throw new MunicipioException("Município duplicado!");
                    }

                    pos = source.getFilePointer();
                    ++line;
                } while (pos < size);
            }

            domain.setId(line);
            source.writeBytes(domain.toCSV(CSV_SEPARATOR));
        } catch (IOException cause) {
            throw new MunicipioException("PROBLEMAS AO INSERIR MUNICÍPIO!", cause);
        }
    }

    @Override
    public void atualizar(Municipio domain) throws MunicipioException {

    }

    @Override
    public void apagar(Municipio domain) throws MunicipioException {

    }

    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        return null;
    }
}
