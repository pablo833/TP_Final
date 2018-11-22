package UI.PROGRAMA;

import ENTIDADES.Productor;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;
import UI.PRODUCTOR.ProductorTable;

import javax.swing.*;
import java.util.List;

public class ListProgramasPanel extends JPanel{
    Handler handler;

    public ListProgramasPanel(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }
    }

    private void initUI() throws RadioException {

        ProgramaTable programaTable = GetProgramas();
        JTable tabla = new JTable(programaTable);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }

    private ProgramaTable GetProgramas() throws RadioException {
        List<Programa> programas = handler.getProgramas();
        return new ProgramaTable(programas);

    }
}
