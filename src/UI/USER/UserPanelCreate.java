package UI.USER;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanelCreate extends UserPanel {

    private Usuario userFound = null;

    public UserPanelCreate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {


        JButton btnOk = new JButton("Ok");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.createUser(createUser());

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
                cleanJText();

            }
        });

        return crearOkBotonera(btnOk);
    }

    protected Usuario createUser() {
        Usuario newUser = null;

        newUser = new Usuario(txtUsername.getText(), String.valueOf(txtPassword.getPassword()),
                txtFirstName.getText(), txtLastName.getText());


        return newUser;
    }

}
