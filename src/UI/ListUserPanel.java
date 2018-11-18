package UI;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Usuario;
import exceptions.RadioException;

public class ListUserPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Handler handler;

	public ListUserPanel(Handler handler) {
		this.handler = handler;
		try {
			initUI();
		} catch (RadioException e) {
			CustomOptionPane.showErrorMessage(e.getMessage());
		}
	}

	private void initUI() throws RadioException {

		UsersTable usrTable = GetUsuarios();
		JTable tabla = new JTable(usrTable);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
	}

	private UsersTable GetUsuarios() throws RadioException {
		List<Usuario> users = handler.GetUsuarios();
		// Usuario.getAll();
		return new UsersTable(users);
	}
}
