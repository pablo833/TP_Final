package BOS;

import DAOS.DAO;
import DAOS.impl.ContratoDAOImpl;
import ENTIDADES.Contrato;
import EXCEPTIONS.RadioException;

public class ContratoBO {

    private DAO contratoDAO;

    public void setDao(ContratoDAOImpl contratoDAOImpl) {
        this.contratoDAO = contratoDAOImpl;
    }

    public void create(Contrato contrato) throws RadioException {
        contratoDAO.insert(contrato);
    }
}
