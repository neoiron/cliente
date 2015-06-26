package repository.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Set;
import java.util.TreeSet;

import repository.MunicipioDAO;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;

public class FileMunicipioDAO extends FileDAO implements MunicipioDAO {

    public static final String FILE_NAME = "municipio.txt";

    @Override
    public void inserir(Municipio domain) throws MunicipioException {
        try (RandomAccessFile source = DataSource.openWriteableFile(FILE_NAME)) {
            final StringBuilder content = new StringBuilder();
            long size = source.length();
            int id = 1;

            if (size > 0) {
                Municipio found;
                String[] fields;
                String line;
                long pos;

                source.seek(FILE_BEGIN);

                line = source.readLine();

                if (line.contains("#")) {
                    id = Integer.valueOf(line.substring(1));
                    content.append("#").append(++id).append(NEW_LINE);
                }

                do {
                    line = source.readLine();
                    fields = line.split(CSV_SPLIT_REGEX);
                    found = new Municipio(
                            fields[Fields.Municipio.NOME.ordinal()], 
                            fields[Fields.Municipio.UF.ordinal()]);

                    content.append(line).append(NEW_LINE);

                    if (found.equals(domain)) {
                        throw new MunicipioException("Município duplicado!");
                    }

                    pos = source.getFilePointer();
                } while (pos < size);
            } else {
                content.append("#").append(id).append(NEW_LINE);
            }

            domain.setId(id);
            content.append(domain.toCSV(CSV_SEPARATOR));
            source.writeBytes(content.toString());
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
        try (RandomAccessFile source = DataSource.openWriteableFile(FILE_NAME)) {
            Set<Municipio> municipios = new TreeSet<>();
            long size = source.length();

            if (size > 0) {
                Municipio municipio;
                String[] fields;
                String line, field;
                long pos;

                source.seek(FILE_BEGIN);

                do {
                    line = source.readLine();
                    fields = line.split(CSV_SPLIT_REGEX);
                    field = fields[Fields.Municipio.UF.ordinal()];

                    if (UFVO.valueOf(field).equals(uf)) {
                        municipio = new Municipio(fields[Fields.Municipio.NOME.ordinal()], uf);
    
                        municipio.setId(Integer.valueOf(fields[Fields.Municipio.ID.ordinal()]));
    
                        municipios.add(municipio);
                    }

                    pos = source.getFilePointer();
                } while (pos < size);
            }

            return municipios;
        } catch (IOException cause) {
            throw new MunicipioException("PROBLEMAS AO APAGAR MUNICÍPIO!", cause);
        }
    }
}
