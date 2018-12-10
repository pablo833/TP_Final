package UI.PROGRAMA;

import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;
import UI.ListEntitiesPanel;

import javax.swing.*;
import java.util.List;

public class ListProgramasPanel extends ListEntitiesPanel {

    private static final String name = "Programas ";

    public ListProgramasPanel(Handler handler) {
        super(handler);
        initUI();
    }

    protected void initUI() {

        ProgramaTable programaTable = null;
        add(getTitleBox(name));
        try {
            programaTable = GetProgramas();
            JTable tabla = new JTable(programaTable);
            JScrollPane scroll = new JScrollPane(tabla);
            add(scroll);
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }
    }

    private ProgramaTable GetProgramas() throws RadioException {
        List<Programa> programas = handler.getProgramas();
        return new ProgramaTable(programas);

    }
}
