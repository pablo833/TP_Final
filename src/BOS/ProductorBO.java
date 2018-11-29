package BOS;

import DAOS.DAO;
import DAOS.impl.ProductorDAOImpl;
import DAOS.impl.ProgramaDAOImpl;
import ENTIDADES.Productor;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;

import java.util.List;

public class ProductorBO {

    private final String CONDUCTOR_EXISTENTE_ERROR = "Ya hay un PRODUCTOR con ese nombre.";
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del productor";
    private final String CONDUCTOR_ASOCIADO_A_UN_PROGRAMA = "El conductor está asociado a un programa. No se puede borrar.";
    private DAO productorDAO;

    public void setDao(ProductorDAOImpl productorDaoImpl) {
        this.productorDAO = productorDaoImpl;
    }

    public Productor getByDNI(Productor productor) throws RadioException {
        return (Productor) productorDAO.getByInternalID(productor);

    }

    public void create(Productor productor) throws RadioException {
        if (esProductorValido(productor)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            if (!doesExistsDNI(productor)) {
                productorDAO.insert(productor);
            } else {
                throw new RadioException(CONDUCTOR_EXISTENTE_ERROR);
            }
        }

    }

    private boolean doesExistsDNI(Productor productor) throws RadioException {
        return this.getByDNI(productor) != null;
    }

    private boolean esProductorValido(Productor productor) {

        return productor.getNombre().isEmpty() || productor.getApellido().isEmpty() || productor.getDni() <= 0;
    }

    public void update(Productor productor) throws RadioException {
        if (esProductorValido(productor)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            productorDAO.update(productor);
        }
    }

    public void delete(Productor productor) throws RadioException {
        if (canDeleteProductor(productor.getCodigo())) {
            productorDAO.delete(productor.getCodigo());
        } else {
            throw new RadioException(CONDUCTOR_ASOCIADO_A_UN_PROGRAMA);
        }

    }

    private boolean canDeleteProductor(int code) throws RadioException {
        DAO programaDAO = new ProgramaDAOImpl();
        Programa programa = ((ProgramaDAOImpl) programaDAO).getByProductor(code);

        return programa == null;
    }

    public List<Productor> getAll() throws RadioException {
        return productorDAO.getAll();
    }
}
