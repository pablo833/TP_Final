package DAOS;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;

import java.util.List;

public interface ConductorDAO {

    public void insert(Conductor conductor) throws RadioException;

    public void update(Conductor conductor) throws RadioException;

    public Conductor get(int codigo) throws RadioException;

    public void delete(int codigo) throws RadioException;

    public List<Conductor> getAll() throws RadioException;
    
}
