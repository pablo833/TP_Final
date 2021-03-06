package BOS;

import DAOS.DAO;
import DAOS.impl.UsuarioDaoImpl;
import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

import java.util.List;

public class UsuarioBO {

    private final String USUARIO_EXISTENTE_ERROR = "Ya hay un usuario con ese nombre de usuario";
    private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del usuario";

    private DAO usuarioDAO;

    public void setDao(UsuarioDaoImpl usuarioDaoImpl) {
        this.usuarioDAO = usuarioDaoImpl;
    }

    public Usuario getByUserName(Usuario user) throws RadioException {

        return (Usuario) usuarioDAO.getByInternalID(user);
    }

    public void create(Usuario user) throws RadioException {

        if (esUsuarioInValido(user)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            if (!doesExistsUserName(user)) {
                usuarioDAO.insert(user);
            } else {
                throw new RadioException(USUARIO_EXISTENTE_ERROR);
            }
        }

    }

    private boolean doesExistsUserName(Usuario user) throws RadioException {

        return getByUserName(user) != null;
    }

    private Boolean esUsuarioInValido(Usuario user) {
        return user.getPassword().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty();
    }

    public void update(Usuario user) throws RadioException {

        if (esUsuarioInValido(user)) {
            throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
        } else {
            usuarioDAO.update(user);
        }
    }

    public void delete(Usuario user) throws RadioException {

        usuarioDAO.delete(user.getCode());
    }

    public List<Usuario> getAll() throws RadioException {

        return usuarioDAO.getAll();
    }


}
