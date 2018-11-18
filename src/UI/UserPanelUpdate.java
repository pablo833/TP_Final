package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import exceptions.RadioException;

public class UserPanelUpdate extends UserPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserPanelUpdate(Handler handler) {
		super(handler);
	}

	@Override
	public Box generateBotonera() {
		Box botonera = Box.createHorizontalBox();
		botonera.add(Box.createHorizontalGlue());
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					handler.updateUser(createUser());
					cleanJText();
				} catch (RadioException e) {
					CustomOptionPane.showErrorMessage(e.getMessage());
				}
			}
		});
		botonera.add(btnOk);

		JButton btnFind = generateFindButton();

		txtFirstName.setEnabled(false);
		txtLastName.setEnabled(false);
		txtPassword.setEnabled(false);

		botonera.add(btnFind);

		botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

		return botonera;
	}

}
