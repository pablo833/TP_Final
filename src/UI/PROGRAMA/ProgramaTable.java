package UI.PROGRAMA;

import ENTIDADES.Productor;
import ENTIDADES.Programa;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProgramaTable extends AbstractTableModel {


    List<Programa> programas = new ArrayList<Programa>();

    private String[] columnsName = {"ID", "nombre", "horario", "valorSegundoAlAire", "conductor", "productor"};

    private static final int ID = 0;
    private static final int NOMBRE = 1;
    private static final int HORARIO = 2;
    private static final int VALORSEGUNDOALAIRE = 3;
    private static final int CONDUCTOR = 4;
    private static final int PRODUCTOR = 5;

    public ProgramaTable(List<Programa> programas) {
        this.programas = programas;
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public int getRowCount() {
        return programas.size();
    }

    @Override
    public Object getValueAt(int row, int col) {

        Programa programa = programas.get(row);

        switch (col) {
            case ID:
                return programa.getCodigo();
            case NOMBRE:
                return programa.getNombre();
            case HORARIO:
                return programa.getHorario();
            case VALORSEGUNDOALAIRE:
                return programa.getValorSegundoAlAire();
            case CONDUCTOR:
                return programa.getConductor().toString();
            case PRODUCTOR:
                return programa.getProductor().toString();
            default:
                return null;
        }
    }

    public Programa getSelectedElement(int row) {

        return programas.get(row);
    }

    public String getColumnName(int col) {

        return columnsName[col];
    }
}


