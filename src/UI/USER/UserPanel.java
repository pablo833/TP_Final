package UI.USER;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;
import UI.AbstractPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UserPanel extends AbstractPanel {

    protected Handler handler;

    protected JTextField txtUsername = null;
    protected JTextField txtFirstName = null;
    protected JTextField txtLastName = null;
    protected JPasswordField txtPassword = null;
    protected JButton btnOk;
    protected Usuario user = null;

    protected UserPanel() {

    }

    protected UserPanel(Handler handler, String title) {
        this.handler = handler;
        initUI(title);
    }

    public static UserPanel create(Handler handler, PanelMode action) {

        UserPanel userPanel = null;
        switch (action) {
            case CREATE:
                userPanel = new UserPanelCreate(handler, action.toString());
                break;
            case UPDATE:
                userPanel = new UserPanelUpdate(handler, action.toString());
                break;
            case DELETE:
                userPanel = new UserPanelDelete(handler, action.toString());
                break;
            default:
                break;
        }

        return userPanel;
    }

    private void initUI(String title) {

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        txtUsername = new JTextField(columnSize);
        Box boxUsername = crearCombo("Nombre de Usuario", txtUsername);

        txtFirstName = new JTextField(columnSize);
        Box boxFirstName = crearCombo("Nombre", txtFirstName);

        txtLastName = new JTextField(columnSize);
        Box boxLastName = crearCombo("Apellido", txtLastName);

        txtPassword = new JPasswordField(columnSize);
        Box boxPassword = crearCombo("Contrase�a", txtPassword);

        Box botonera = generateBotonera();

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxUsername);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxPassword);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxFirstName);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxLastName);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(botonera);

        add(panel);
    }

    @Override
    protected Box generateBotonera() {
        return null;
    }

    protected JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        enableEditControls(false);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                user = null;
                try {
                    user = handler.getUser(txtUsername.getText());

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }

                if (user != null) {
                    txtUsername.setText(user.getUserName());
                    txtFirstName.setText(user.getFirstName());
                    txtLastName.setText(user.getLastName());
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPane.showInformationMessage("Usuario no encontrado.");
                }
            }
        });

        return btnFind;
    }

    protected void cleanJText() {
        txtUsername.setText(null);
        txtPassword.setText(null);
        txtFirstName.setText(null);
        txtLastName.setText(null);
    }

    protected Usuario createUser() {
        Usuario newUser = null;
        if (user != null) {
            newUser = new Usuario(user.getCode(), txtUsername.getText(), String.valueOf(txtPassword.getPassword()),
                    txtFirstName.getText(), txtLastName.getText());
        } else {
            newUser = new Usuario(txtUsername.getText(), String.valueOf(txtPassword.getPassword()),
                    txtFirstName.getText(), txtLastName.getText());

        }

        return newUser;
    }

    protected void enableEditControls(boolean var) {
        txtFirstName.setEnabled(var);
        txtLastName.setEnabled(var);
        txtPassword.setEnabled(var);
    }
}
