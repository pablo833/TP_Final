package UI.CONDUCTOR;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;
import UI.ListEntitiesPanel;

import javax.swing.*;
import java.util.List;

public class ListConductoresPanel extends ListEntitiesPanel {

    private static final String name = "Conductores";

    public ListConductoresPanel(Handler handler) {
        super(handler);
        initUI();
    }

    protected void initUI() {

        ConductorTable conductoresTable = null;
        add(getTitleBox(name));
        try {
            conductoresTable = GetConductores();
            JTable tabla = new JTable(conductoresTable);
            JScrollPane scroll = new JScrollPane(tabla);
            add(scroll);
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }
    }

    private ConductorTable GetConductores() throws RadioException {
        List<Conductor> conductores = handler.getConductores();
        return new ConductorTable(conductores);
    }
}
