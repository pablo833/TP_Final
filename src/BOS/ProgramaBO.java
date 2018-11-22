package BOS;

import DAOS.DAO;
import DAOS.impl.ProgramaDAOImpl;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;

import java.util.List;

public class ProgramaBO {
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del PROGRAMA";

    private final String PROGRAMA_EXISTENTE_ERROR = "Ya hay un PROGRAMA con ese nombre ";
    private DAO programaDAO;

    public void setDao(ProgramaDAOImpl programaDaoImp) {
        this.programaDAO = programaDaoImp;
    }

    public void create(Programa programa) throws RadioException {

        if (esProgramaInValido(programa)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            if (!doesExistsPrograma(programa)) {
                programaDAO.insert(programa);
            } else {
                throw new RadioException(PROGRAMA_EXISTENTE_ERROR);
            }
        }
    }

    private boolean esProgramaInValido(Programa programa) {
        return programa.getNombre().isEmpty() ||
                programa.getHorario().isEmpty() ||
                programa.getValorSegundoAlAire() <= 0 ||
                programa.getConductor() == null ||
                programa.getProductor() == null;
    }

    private boolean doesExistsPrograma(Programa programa) throws RadioException {
        return getByNombre(programa) != null;
    }

    private Programa getByNombre(Programa programa) throws RadioException {
        return (Programa) programaDAO.getByInternalID(programa);
    }

    public List<Programa> getAll() throws RadioException {
        return programaDAO.getAll();
    }
}
