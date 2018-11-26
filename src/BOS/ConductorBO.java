package BOS;

import DAOS.DAO;
import DAOS.impl.ConductorDAOImpl;
import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;

import java.util.List;

public class ConductorBO {

    private final String CONDUCTOR_EXISTENTE_ERROR = "Ya hay un CONDUCTOR con ese nombre de usuario";
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del conductor";

    private DAO conductorDAO;

    public void setDao(ConductorDAOImpl conductorDaoImpl) {
        this.conductorDAO = conductorDaoImpl;
    }

    public Conductor getByDNI(Conductor conductor) throws RadioException {
        return (Conductor) conductorDAO.getByInternalID(conductor);

    }

    public void create(Conductor conductor) throws RadioException {
        if (esConductorValido(conductor)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            if (!doesExistsDNI(conductor)) {
                conductorDAO.insert(conductor);
            } else {
                throw new RadioException(CONDUCTOR_EXISTENTE_ERROR);
            }
        }

    }

    private boolean doesExistsDNI(Conductor conductor) throws RadioException {
        return this.getByDNI(conductor) != null;
    }

    private boolean esConductorValido(Conductor conductor) {

        return conductor.getNombre().isEmpty() || conductor.getApellido().isEmpty() || conductor.getDni() <= 0 || conductor.getSueldo() <= 0;
    }

    public void update(Conductor conductor) throws RadioException {
        if (esConductorValido(conductor)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            conductorDAO.update(conductor);
        }
    }

    public void delete(Conductor conductor) throws RadioException {
        conductorDAO.delete(conductor.getCodigo());
    }

    public List<Conductor> getAll() throws RadioException {
        return conductorDAO.getAll();
    }
}
