package UI.AUSPICIANTE;

import UI.CustomOptionPane;

import entidades.Auspiciante;
import exceptions.RadioException;

import UI.Handler;

import javax.swing.*;
import java.util.List;

public class ListAuspiciantePane extends JPanel {

    Handler handler;

    public ListAuspiciantePane(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
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
