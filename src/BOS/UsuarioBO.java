package BOS;

import java.sql.SQLException;
import java.util.List;

import DAOS.UsuarioDAO;
import DAOS.impl.UsuarioDaoImpl;
import entidades.Usuario;
import exceptions.RadioException;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO;
	private final String USUARIO_EXISTENTE_ERROR = "Ya hay un usuario con ese nombre de usuario";
	private final String DATOS_OBLIGATORIOS_ERROR = "Debe completar todos los datos del usuario";

	public void setDao(UsuarioDaoImpl usuarioDaoImpl) {
		this.usuarioDAO = usuarioDaoImpl;

	}

	public void create(Usuario user) throws RadioException, SQLException {

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

	private boolean doesExistsUserName(Usuario user) throws RadioException, SQLException {

		if (getByUserName(user) == null) {
			return false;
		}
		return true;
	}

	public void update(Usuario user) throws RadioException, SQLException {

		if (esUsuarioInValido(user)) {
			throw new RadioException(DATOS_OBLIGATORIOS_ERROR);
		} else {
			usuarioDAO.update(user);
		}
	}

	public void delete(Usuario user) throws RadioException, SQLException {

		usuarioDAO.delete(user.getUserName());
	}

	public Usuario getByUserName(Usuario user) throws RadioException, SQLException {

		return usuarioDAO.getByUserName(user.getUserName());
	}

	public List<Usuario> getAll() throws RadioException {

		return usuarioDAO.getAll();
	}

	private Boolean esUsuarioInValido(Usuario user) {
		return user.getPassword().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty();
	}
}
