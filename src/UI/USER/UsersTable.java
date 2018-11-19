package UI.USER;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ENTIDADES.Usuario;

public class UsersTable extends AbstractTableModel {

    List<Usuario> users = new ArrayList<Usuario>();

    private String[] columnsName = {"id", "userName", "password", "firstName", "lastName"};

    private static final int ID = 0;
    private static final int USERNAME = 1;
    private static final int PASSWORD = 2;
    private static final int FIRSTNAME = 3;
    private static final int LASTNAME = 4;

    public UsersTable(List<Usuario> users) {
        this.users = users;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Usuario user = users.get(row);

        switch (col) {
            case ID:
                return user.getCode();
            case USERNAME:
                return user.getUserName();
            case PASSWORD:
                return user.getPassword();
            case FIRSTNAME:
                return user.getFirstName();
            case LASTNAME:
                return user.getLastName();
            default:
                return null;
        }
    }

    public Usuario getSelectedElement(int row) {
        return users.get(row);
    }

    public String getColumnName(int col) {
        return columnsName[col];
    }
}
