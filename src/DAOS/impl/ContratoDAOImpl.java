package DAOS.impl;

import DAOS.DAO;
import ENTIDADES.Contrato;
import EXCEPTIONS.RadioException;

import java.util.List;


public class ContratoDAOImpl extends AbstractImpl implements DAO<Contrato> {

    @Override
    public void insert(Contrato contrato) throws RadioException {

    }

    @Override
    public void update(Contrato contrato) throws RadioException {

    }

    @Override
    public Contrato get(int codigo) throws RadioException {
        return null;
    }

    @Override
    public void delete(int codigo) throws RadioException {

    }

    @Override
    public List<Contrato> getAll() throws RadioException {
        return null;
    }

    @Override
    public Contrato getByInternalID(Contrato contrato) throws RadioException {
        return null;
    }
}
