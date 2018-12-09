package UI;

import ENTIDADES.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends BasePanel {

    private final int horizontalStructureSize = 10;
    JTextField txtUsername = null;
    JPasswordField txtPassword = null;

    private Handler handler;

    public LoginPanel(Handler handler) {
        this.handler = handler;
        initUI();

    }

    private void initUI() {

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel("LoginPanel");
        boxTitle.add(lblTitle);

        txtUsername = new JTextField(columnSize);
        Box boxUsername = crearCombo("Nombre de Usuario", txtUsername);

        txtPassword = new JPasswordField(columnSize);
        Box boxPassword = crearCombo("Contraseña", txtPassword);

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

    public Box generateBotonera() {

        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {

                    handler.login(createUser());

                } catch (Exception e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

            }

            private Usuario createUser() {
                Usuario user = new Usuario(txtUsername.getText(),
                        String.valueOf(txtPassword.getPassword()),
                        null,
                        null);
                return user;
            }
        });
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;

    }

}
