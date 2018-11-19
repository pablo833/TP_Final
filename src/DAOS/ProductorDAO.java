package DAOS;

import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;

import java.util.List;

public interface ProductorDAO {
    public void insert(Productor productor) throws RadioException;

    public void update(Productor productor) throws RadioException;

    public Productor get(int codigo) throws RadioException;

    public void delete(int codigo) throws RadioException;

    public List<Productor> getAll() throws RadioException;
}
