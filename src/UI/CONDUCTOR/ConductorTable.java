package UI.CONDUCTOR;

import ENTIDADES.Conductor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ConductorTable extends AbstractTableModel {

    List<Conductor> conductores = new ArrayList<Conductor>();

    private String[] columnsName = {"id", "nombre", "apellido", "dni", "sueldo"};

    private static final int ID = 0;
    private static final int NOMBRE = 1;
    private static final int APELLIDO = 2;
    private static final int DNI = 3;
    private static final int SUELDO = 4;

    public ConductorTable(List<Conductor> conductores) {
        this.conductores = conductores;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public int getRowCount() {
        return conductores.size();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Conductor conductor = conductores.get(row);

        switch (col) {
            case ID:
                return conductor.getCodigo();
            case NOMBRE:
                return conductor.getNombre();
            case APELLIDO:
                return conductor.getApellido();
            case DNI:
                return conductor.getDni();
            case SUELDO:
                return conductor.getSueldo();
            default:
                return null;
        }
    }

    public Conductor getSelectedElement(int row) {

        return conductores.get(row);
    }

    public String getColumnName(int col) {

        return columnsName[col];
    }
}
