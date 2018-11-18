package UI;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import BOS.UsuarioBO;
import DAOS.impl.UsuarioDaoImpl;
import entidades.Usuario;
import exceptions.RadioException;

public class Handler {
	private MainContianerFrame containerFrame;
	private UsuarioBO usuarioBO;
	private final String USUARIO_CONTRASENIA_INEXISTENTE = "Usuario o contrasia invalidos";

	public Handler() {
		this.usuarioBO = new UsuarioBO();
		this.usuarioBO.setDao(new UsuarioDaoImpl());
	}

	public void initApp() {
		containerFrame = new MainContianerFrame("Radio App", this);
		containerFrame.setVisible(true);
	}

	public void createUser(Usuario user) throws RadioException {

		try {
			usuarioBO.create(user);
			CustomOptionPane.showInformationMessage("Operaci�n exitosa");
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public void updateUser(Usuario user) throws RadioException {

		try {
			usuarioBO.update(user);
			CustomOptionPane.showInformationMessage("Operaci�n exitosa");

		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public void deleteUser(Usuario user) throws RadioException {

		try {
			usuarioBO.delete(user);
			CustomOptionPane.showInformationMessage("Operaci�n exitosa");
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public Usuario getUser(String userName) throws RadioException {
		Usuario user = new Usuario(userName, null, null, null);
		try {
			user = usuarioBO.getByUserName(user);
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		}
		return user;
	}

	public Usuario getUser(Usuario usuario) throws RadioException {
		Usuario userFound = null;

		try {
			userFound = usuarioBO.getByUserName(usuario);
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		}

		return userFound;

	}

	public List<Usuario> GetUsuarios() throws RadioException {
		return usuarioBO.getAll();
	}

	public void showLogin() {
		containerFrame.changePanel(new Login(this));
	}

	public void addCreateUserPane() {
		containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.CREATE));
	}

	public void addUpdateUserPane() {
		containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.UPDATE));
	}

	public void addDeleteUserPane() {
		containerFrame.changePanel(UserPanel.create(this, UserPanel.PanelMode.DELETE));
	}

	public void addListUsersPane() {
		containerFrame.changePanel(new ListUserPanel(this));

	}

	public void login(Usuario user) throws RadioException {
		Usuario userFound = null;

		try {
			userFound = usuarioBO.getByUserName(user);
			if (userFound == null) {
				CustomOptionPane.showErrorMessage(USUARIO_CONTRASENIA_INEXISTENTE);
			} else {
				containerFrame.removePanel();
				containerFrame.setMenuBarVisible();
			}
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		}
	}

}
