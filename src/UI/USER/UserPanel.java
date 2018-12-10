package UI.USER;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;
import UI.BasePanel;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UserPanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Usuarios ";

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
                userPanel = new UserPanelCreate(handler, name + action.toString());
                break;
            case UPDATE:
                userPanel = new UserPanelUpdate(handler, name + action.toString());
                break;
            case DELETE:
                userPanel = new UserPanelDelete(handler, name + action.toString());
                break;
            default:
                break;
        }

        return userPanel;
    }

    private void initUI(String title) {

        Box panel = Box.createVerticalBox();
        panel.add(getTitleBox(title));
        panel.add(getUsernameBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getPasswordBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getFirstNameBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getLastNameBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(generateBotonera());

        add(panel);
    }

    private Box getPasswordBox() {
        txtPassword = new JPasswordField(columnSize);
        return crearCombo("Contraseña", txtPassword);
    }

    private Box getLastNameBox() {
        txtLastName = new JTextField(columnSize);
        return crearCombo("Apellido", txtLastName);
    }

    private Box getFirstNameBox() {
        txtFirstName = new JTextField(columnSize);
        return crearCombo("Nombre", txtFirstName);
    }

    private Box getUsernameBox() {
        txtUsername = new JTextField(columnSize);
        return crearCombo("Nombre de Usuario", txtUsername);
    }

    protected abstract Box generateBotonera();

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
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (user != null) {
                    txtUsername.setText(user.getUserName());
                    txtFirstName.setText(user.getFirstName());
                    txtLastName.setText(user.getLastName());
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Usuario no encontrado.");
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
