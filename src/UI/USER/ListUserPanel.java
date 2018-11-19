package UI.USER;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import UI.CustomOptionPane;
import UI.Handler;
import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

public class ListUserPanel extends JPanel {

    Handler handler;

    public ListUserPanel(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    private void initUI() throws RadioException {

        UsersTable usrTable = GetUsuarios();
        JTable tabla = new JTable(usrTable);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }

    private UsersTable GetUsuarios() throws RadioException {
        List<Usuario> users = handler.GetUsuarios();
        // Usuario.getAll();
        return new UsersTable(users);
    }
}
