package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import exceptions.RadioException;

public class UserPanelCreate extends UserPanel {

	private static final long serialVersionUID = 1L;

	public UserPanelCreate(Handler handler) {
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
					handler.createUser(createUser());

				} catch (RadioException e) {
					CustomOptionPane.showErrorMessage(e.getMessage());
				}
				cleanJText();

			}
		});
		botonera.add(btnOk);

		botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

		return botonera;

	}

}
