package UI;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import BOS.UsuarioBO;
import DAOS.impl.UsuarioDaoImpl;
import entidades.Usuario;
import exceptions.RadioException;

//8/15/22

public class Handler {
	private MainContianerFrame containerFrame;
	private UsuarioBO usuarioBO;

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
			CustomOptionPane.showInformationMessage("Operación exitosa");
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public void updateUser(Usuario user) throws RadioException {

		try {
			usuarioBO.update(user);
			CustomOptionPane.showInformationMessage("Operación exitosa");

		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public void deleteUser(Usuario user) throws RadioException {

		try {
			usuarioBO.delete(user);
			CustomOptionPane.showInformationMessage("Operación exitosa");
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		} catch (Exception e1) {
			CustomOptionPane.showErrorMessage(e1.getMessage());
		}
	}

	public Usuario getUser(String userName) throws RadioException, SQLException {
		Usuario user = new Usuario(userName, null, null, null);
		return usuarioBO.getByUserName(user);
	}

	public Usuario getUser(Usuario usuario) throws SQLException {
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
}
