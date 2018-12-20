package UI.USER;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanelDelete extends UserPanel {

    private Usuario userFound = null;

    public UserPanelDelete(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    public Box generateBotonera() {

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.deleteUser(createUser());
                    btnOk.setEnabled(false);
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
            }
        });

        btnOk.setEnabled(false);

        Box botonera = crearOkBotonera(btnOk);

        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        enableEditControls(false);

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    private JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        enableEditControls(false);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                userFound = null;
                try {
                    userFound = handler.getUser(txtUsername.getText());

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (userFound != null) {
                    txtUsername.setText(userFound.getUserName());
                    txtFirstName.setText(userFound.getFirstName());
                    txtLastName.setText(userFound.getLastName());
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Usuario no encontrado.");
                }
            }
        });

        return btnFind;
    }

    protected Usuario createUser() {
        Usuario newUser = null;
        if (userFound != null) {
            newUser = new Usuario(userFound.getCode(), txtUsername.getText(), String.valueOf(txtPassword.getPassword()),
                    txtFirstName.getText(), txtLastName.getText());
        } else {
            newUser = new Usuario(txtUsername.getText(), String.valueOf(txtPassword.getPassword()),
                    txtFirstName.getText(), txtLastName.getText());

        }

        return newUser;
    }
}
