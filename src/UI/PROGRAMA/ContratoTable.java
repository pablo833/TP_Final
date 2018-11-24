package UI.PROGRAMA;

import ENTIDADES.Contrato;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ContratoTable extends AbstractTableModel {

    List<Contrato> contratos = new ArrayList<Contrato>();

    private String[] columnsName = {"auspiciante", "tiempoDePauta"};

    private static final int AUSPICIANTE = 0;
    private static final int TIEMPODEPAUTA = 1;

    public ContratoTable(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public int getRowCount() {
        return contratos.size();
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Contrato contrato = contratos.get(row);
        switch (col) {
            case AUSPICIANTE:
                return contrato.getAuspiciante().getRazonSocial();
            case TIEMPODEPAUTA:
                return contrato.getTiempoDePauta();
            default:
                return null;
        }
    }

    public Contrato getSelectedElement(int row) {

        return contratos.get(row);
    }

    public String getColumnName(int col) {

        return columnsName[col];
    }
}
