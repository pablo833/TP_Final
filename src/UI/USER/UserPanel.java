package UI.USER;

import UI.BasePanel;
import UI.Handler;

import javax.swing.*;

public abstract class UserPanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Usuarios ";

    protected JTextField txtUsername = null;
    protected JTextField txtFirstName = null;
    protected JTextField txtLastName = null;
    protected JPasswordField txtPassword = null;
    protected JButton btnOk;

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

    protected void cleanJText() {
        txtUsername.setText(null);
        txtPassword.setText(null);
        txtFirstName.setText(null);
        txtLastName.setText(null);
    }

    protected void enableEditControls(boolean var) {
        txtFirstName.setEnabled(var);
        txtLastName.setEnabled(var);
        txtPassword.setEnabled(var);
    }
}
