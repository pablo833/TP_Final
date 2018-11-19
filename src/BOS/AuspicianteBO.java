package BOS;

import DAOS.DAO;
import DAOS.impl.AuspicianteDaoImpl;
import ENTIDADES.Auspiciante;

import EXCEPTIONS.RadioException;

import java.util.List;

public class AuspicianteBO {

    private final String AUSPICIANTE_EXISTENTE_ERROR = "Ya hay un AUSPICIANTE con ese nombre ";
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del AUSPICIANTE";

    private DAO auspicianteDAO;

    public Auspiciante getByRazonSocial(Auspiciante auspiciante) throws RadioException {

        return (Auspiciante) auspicianteDAO.getByInternalID(auspiciante);
    }

    public void setDao(AuspicianteDaoImpl auspicianteDaoImp) {

        this.auspicianteDAO = auspicianteDaoImp;
    }

    public void create(Auspiciante auspiciante) throws RadioException {
        if (esAuspicianteInValido(auspiciante)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            if (!doesExistsAuspiciante(auspiciante)) {
                auspicianteDAO.insert(auspiciante);
            } else {
                throw new RadioException(AUSPICIANTE_EXISTENTE_ERROR);
            }
        }
    }

    private boolean doesExistsAuspiciante(Auspiciante auspiciante) throws RadioException {
        return getByRazonSocial(auspiciante) != null;
    }

    private boolean esAuspicianteInValido(Auspiciante auspiciante) {

        return auspiciante.getRazonSocial().isEmpty();
    }

    public void update(Auspiciante auspiciante) throws RadioException {

        if (esAuspicianteInValido(auspiciante)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            auspicianteDAO.update(auspiciante);
        }
    }

    public List<Auspiciante> getAll() throws RadioException {

        return auspicianteDAO.getAll();
    }


}
