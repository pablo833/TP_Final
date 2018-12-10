package UI.USER;

import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;
import UI.ListEntitiesPanel;

import javax.swing.*;
import java.util.List;

public class ListUserPanel extends ListEntitiesPanel {

    private static final String name = "Usuarios ";

    public ListUserPanel(Handler handler) {
        super(handler);

        initUI();
    }

    protected void initUI() {

        UsersTable usrTable = null;

        add(getTitleBox(name));
        try {
            usrTable = GetUsuarios();
            JTable tabla = new JTable(usrTable);
            JScrollPane scroll = new JScrollPane(tabla);
            add(scroll);
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }
    }

    private UsersTable GetUsuarios() throws RadioException {

        List<Usuario> users = handler.GetUsuarios();
        return new UsersTable(users);
    }
}
