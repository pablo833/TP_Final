package UI.USER;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.*;
import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

public abstract class UserPanel extends JPanel {

    protected Handler handler;

    protected final int horizontalStructureSize = 10;
    protected JTextField txtUsername = null;
    protected JTextField txtFirstName = null;
    protected JTextField txtLastName = null;
    protected JPasswordField txtPassword = null;
    protected JButton btnOk;
    protected Usuario user = null;

    public static enum PanelMode {
        CREATE, UPDATE, DELETE;
    }

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
                userPanel = new UserPanelCreate(handler, PanelMode.CREATE.toString());
                break;
            case UPDATE:
                userPanel = new UserPanelUpdate(handler, PanelMode.UPDATE.toString());
                break;
            case DELETE:
                userPanel = new UserPanelDelete(handler, PanelMode.DELETE.toString());
                break;
            default:
                break;
        }

        return userPanel;
    }

    private void initUI(String title) {
        final int columnSize = 30;
        final int verticalStructureSize = 20;

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        txtUsername = new JTextField(columnSize);
        Box boxUsername = crearCombo(horizontalStructureSize, "Nombre de Usuario", txtUsername);

        txtFirstName = new JTextField(columnSize);
        Box boxFirstName = crearCombo(horizontalStructureSize, "Nombre", txtFirstName);

        txtLastName = new JTextField(columnSize);
        Box boxLastName = crearCombo(horizontalStructureSize, "Apellido", txtLastName);

        txtPassword = new JPasswordField(columnSize);
        Box boxPassword = crearCombo(horizontalStructureSize, "Contrase�a", txtPassword);

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

    private Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component) {
        Box boxUsername = Box.createHorizontalBox();
        JLabel lblUserName = new JLabel(labelText);
        boxUsername.add(lblUserName);
        boxUsername.add(Box.createHorizontalStrut(horizontalStructureSize));
        boxUsername.add(component);
        return boxUsername;
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
