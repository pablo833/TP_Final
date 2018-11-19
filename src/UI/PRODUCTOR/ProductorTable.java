package UI.PRODUCTOR;

import ENTIDADES.Conductor;
import ENTIDADES.Productor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductorTable extends AbstractTableModel {

    List<Productor> productors = new ArrayList<Productor>();

    private String[] columnsName = {"id", "nombre", "apellido", "dni"};

    private static final int ID = 0;
    private static final int NOMBRE = 1;
    private static final int APELLIDO = 2;
    private static final int DNI = 3;
    private static final int SUELDO = 4;

    public ProductorTable(List<Productor> productors) {
        this.productors = productors;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public int getRowCount() {
        return productors.size();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Productor productor = productors.get(row);

        switch (col) {
            case ID:
                return productor.getCodigo();
            case NOMBRE:
                return productor.getNombre();
            case APELLIDO:
                return productor.getApellido();
            case DNI:
                return productor.getDni();
            default:
                return null;
        }
    }

    public Productor getSelectedElement(int row) {

        return productors.get(row);
    }

    public String getColumnName(int col) {

        return columnsName[col];
    }
}
