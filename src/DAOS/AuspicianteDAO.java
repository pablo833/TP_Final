package DAOS;

import java.util.List;

import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;

public interface AuspicianteDAO {

    public void insert(Auspiciante auspiciante) throws RadioException;

    public void update(Auspiciante auspiciante) throws RadioException;

    public Auspiciante get(int codigo) throws RadioException;

    public void delete(int codigo) throws RadioException;

    public void delete(String razonSocial) throws RadioException;

    public List<Auspiciante> getAll() throws RadioException;

    public Auspiciante getByRazonSocial(String razonSocial) throws RadioException;

}
