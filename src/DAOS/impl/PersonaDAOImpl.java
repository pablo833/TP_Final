package DAOS.impl;

import DAOS.DAO;
import ENTIDADES.Persona;
import EXCEPTIONS.RadioException;

import java.util.List;

public class PersonaDAOImpl extends AbstractImpl implements DAO<Persona> {

    @Override
    public void insert(Persona persona) throws RadioException {

    }

    @Override
    public void update(Persona persona) throws RadioException {

    }

    @Override
    public Persona get(int codigo) throws RadioException {
        return null;
    }

    @Override
    public void delete(int codigo) throws RadioException {

    }

    @Override
    public List<Persona> getAll() throws RadioException {
        return null;
    }

    @Override
    public Persona getByInternalID(Persona persona) throws RadioException {
        return null;
    }
}
