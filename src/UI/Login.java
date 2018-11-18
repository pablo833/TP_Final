package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.InitialContext;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Usuario;
import exceptions.RadioException;

public class Login extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int horizontalStructureSize = 10;
	JTextField txtUsername = null;
	JPasswordField txtPassword = null;

	private Handler handler;

	public Login(Handler handler) {
		this.handler = handler;
		initUI();

	}

	private void initUI() {// TODO Auto-generated method stub

		final int columnSize = 30;
		final int verticalStructureSize = 20;
		final int horizontalStructureSize = 10;

		Box boxTitle = Box.createHorizontalBox();
		JLabel lblTitle = new JLabel("Login");
		boxTitle.add(lblTitle);

		txtUsername = new JTextField(columnSize);
		Box boxUsername = crearCombo(horizontalStructureSize, "Nombre de Usuario", txtUsername);

		txtPassword = new JPasswordField(columnSize);
		Box boxPassword = crearCombo(horizontalStructureSize, "Contrasenia", txtPassword);

		Box botonera = generateBotonera();

		Box panel = Box.createVerticalBox();
		panel.add(boxTitle);
		panel.add(Box.createVerticalStrut(verticalStructureSize));
		panel.add(boxUsername);
		panel.add(Box.createVerticalStrut(verticalStructureSize));
		panel.add(boxPassword);
		panel.add(Box.createVerticalStrut(verticalStructureSize));

		panel.add(botonera);

		add(panel);

	}

	private Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component) {
		Box boxUsername = Box.createHorizontalBox();
		JLabel lblUserName = new JLabel(labelText);
		boxUsername.add(lblUserName);
		boxUsername.add(Box.createHorizontalStrut(horizontalStructureSize));
		boxUsername.add(component);
		return boxUsername;
	}

	public Box generateBotonera() {

		Box botonera = Box.createHorizontalBox();
		botonera.add(Box.createHorizontalGlue());
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {

					handler.login(createUser());

				} catch (Exception e) {// (RadioException e) {
					CustomOptionPane.showErrorMessage(e.getMessage());
				}

			}

			private Usuario createUser() {
				Usuario user = new Usuario(txtUsername.getText(), String.valueOf(txtPassword.getPassword()), null,
						null);
				return user;
			}
		});
		botonera.add(btnOk);

		botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

		return botonera;

	}

}
