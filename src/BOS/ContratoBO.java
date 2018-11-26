package BOS;

import DAOS.ContratoDAO;
import DAOS.DAO;
import DAOS.impl.ContratoDAOImpl;
import ENTIDADES.Contrato;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;

import java.util.List;

public class ContratoBO {
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar el tiempo de la pauta";
    private DAO contratoDAO;
    private ContratoDAO espcontratoDAO;

    public void setDao(ContratoDAOImpl contratoDAOImpl) {
        this.contratoDAO = contratoDAOImpl;
        this.espcontratoDAO = contratoDAOImpl;
    }

    public void create(Contrato contrato) throws RadioException {
        if (esUnContratoValido(contrato)) {
            contratoDAO.insert(contrato);
        } else {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        }
    }

    public List<Contrato> getByPrograma(Programa programa) throws RadioException {
        return espcontratoDAO.getByPrograma(programa.getCodigo());
    }

    private boolean esUnContratoValido(Contrato contrato) {
        return contrato.getTiempoDePauta() > 0;
    }

    public void delete(Contrato contrato) throws RadioException {
        contratoDAO.delete(contrato.getCodigo());
    }
}
