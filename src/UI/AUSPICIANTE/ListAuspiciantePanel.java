package UI.AUSPICIANTE;

import UI.CustomOptionPanel;

import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;

import UI.Handler;

import javax.swing.*;
import java.util.List;

public class ListAuspiciantePanel extends JPanel {

    Handler handler;

    public ListAuspiciantePanel(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }
    }

    private void initUI() throws RadioException {

        AuspiciantesTable auspiciantesTable = GetAuspiciantes();
        JTable tabla = new JTable(auspiciantesTable);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }

    private AuspiciantesTable GetAuspiciantes() throws RadioException {
        List<Auspiciante> auspiciantes = handler.GetAuspiciantes();

        return new AuspiciantesTable(auspiciantes);
    }

}
