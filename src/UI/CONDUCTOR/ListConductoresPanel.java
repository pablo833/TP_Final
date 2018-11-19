package UI.CONDUCTOR;

import javax.swing.*;
import java.util.List;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;



public class ListConductoresPanel extends JPanel {
    Handler handler;

    public ListConductoresPanel(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    private void initUI() throws RadioException {

        ConductorTable conductoresTable = GetConductores();
        JTable tabla = new JTable(conductoresTable);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }

    private ConductorTable GetConductores() throws RadioException {
        List<Conductor> conductores = handler.getConductores();
        return new ConductorTable(conductores);
    }
}
