package UI.AUSPICIANTE;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ENTIDADES.Auspiciante;

public class AuspiciantesTable extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    List<Auspiciante> auspiciantes = new ArrayList<Auspiciante>();

    private String[] columnsName = {"id", "razonSocial"};

    private static final int ID = 0;
    private static final int RAZONSOCIAL = 1;


    public AuspiciantesTable(List<Auspiciante> auspiciantes) {
        this.auspiciantes = auspiciantes;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public int getRowCount() {
        return auspiciantes.size();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Auspiciante auspiciante = auspiciantes.get(row);

        switch (col) {
            case ID:
                return auspiciante.getCode();

            case RAZONSOCIAL:
                return auspiciante.getRazonSocial();

            default:
                return null;
        }
    }

    public Auspiciante getSelectedElement(int row) {
        return auspiciantes.get(row);
    }

    public String getColumnName(int col) {
        return columnsName[col];
    }
}

