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
                    fields = source.readLine().split(CSV_SPLIT_REGEX);
                    found = new Municipio(
                            fields[Fields.Municipio.NOME.ordinal()], 
                            fields[Fields.Municipio.UF.ordinal()]);

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
        try (RandomAccessFile source = DataSource.openWriteableFile(FILE_NAME)) {
            long size = source.length();

            if (size > 0) {
                StringBuilder content = new StringBuilder();
                String[] fields;
                String line;
                Integer id;
                long pos;
                boolean found;

                source.seek(FILE_BEGIN);

                do {
                    line = source.readLine();
                    fields = line.split(CSV_SPLIT_REGEX);
                    id = Integer.valueOf(fields[Fields.Municipio.ID.ordinal()]);
                    found = id.equals(domain.getId());

                    if (found) {
                        content.append(domain.toCSV(CSV_SEPARATOR));
                    } else {
                        content.append(line).append(NEW_LINE);
                    }

                    pos = source.getFilePointer();
                } while (pos < size);

                if (found) {
                    source.seek(FILE_BEGIN);
                    source.setLength(FILE_EMPTY);
                    source.writeBytes(content.toString());
                }
            }
        } catch (IOException cause) {
            throw new MunicipioException("PROBLEMAS AO ATUALIZAR MUNICÍPIO!", cause);
        }
    }

    @Override
    public void apagar(Municipio domain) throws MunicipioException {
        try (RandomAccessFile source = DataSource.openWriteableFile(FILE_NAME)) {
            long size = source.length();

            if (size > 0) {
                StringBuilder content = new StringBuilder();
                String[] fields;
                String line;
                Integer id;
                long pos;

                source.seek(FILE_BEGIN);

                do {
                    line = source.readLine();
                    fields = line.split(CSV_SPLIT_REGEX);
                    id = Integer.valueOf(fields[Fields.Municipio.ID.ordinal()]);

                    if (!id.equals(domain.getId())) {
                        content.append(line).append(NEW_LINE);
                    }

                    pos = source.getFilePointer();
                } while (pos < size);

                source.seek(FILE_BEGIN);
                source.setLength(FILE_EMPTY);
                source.writeBytes(content.toString());
            }
        } catch (IOException cause) {
            throw new MunicipioException("PROBLEMAS AO APAGAR MUNICÍPIO!", cause);
        }
    }

    @Override
    public Set<Municipio> selecionar(UFVO uf) throws MunicipioException {
        return null;
    }
}
