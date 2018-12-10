package UI.AUSPICIANTE;

import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;
import UI.ListEntitiesPanel;

import javax.swing.*;
import java.util.List;

public class ListAuspiciantePanel extends ListEntitiesPanel {

    private static final String name = "Auspiciantes";

    public ListAuspiciantePanel(Handler handler) {
        super(handler);
        initUI();
    }

    protected void initUI() {
        AuspiciantesTable auspiciantesTable = null;
        add(getTitleBox(name));
        try {
            auspiciantesTable = GetAuspiciantes();
            JTable tabla = new JTable(auspiciantesTable);
            JScrollPane scroll = new JScrollPane(tabla);
            add(scroll);
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }

    }

    private AuspiciantesTable GetAuspiciantes() throws RadioException {
        List<Auspiciante> auspiciantes = handler.GetAuspiciantes();
        return new AuspiciantesTable(auspiciantes);
    }

}
