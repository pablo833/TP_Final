package UI.USER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

import UI.CustomOptionPane;
import UI.Handler;
import UI.USER.UserPanel;
import exceptions.RadioException;

public class UserPanelDelete extends UserPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserPanelDelete(Handler handler, String title) {
        super(handler, title);
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
                    handler.deleteUser(createUser());
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
