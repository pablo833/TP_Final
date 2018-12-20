package UI.PROGRAMA;

import UI.AUSPICIANTE.AuspiciantesTable;
import UI.BasePanel;
import UI.Handler;

import javax.swing.*;
import java.awt.*;

public abstract class ProgramaPanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Programas ";

    protected JTextField txtNombre;
    protected JTextField txthorario;
    protected JTextField txtValorSegundoAlAire;
    protected JComboBox cmbCondutor;
    protected JComboBox cmbProductor;
    protected JButton btnOk;


    protected ProgramaPanel() {

    }

    protected ProgramaPanel(Handler handler, String title) {
        this.handler = handler;
        initUI(title);
    }

    public static ProgramaPanel create(Handler handler, BasePanel.PanelMode action) {
        ProgramaPanel programaPanel = null;
        switch (action) {
            case CREATE:
                programaPanel = new ProgramaPanelCreate(handler, name + PanelMode.CREATE.toString());
                break;
            case UPDATE:
                programaPanel = new ProgramaPanelUpdate(handler, name + PanelMode.UPDATE.toString());
                break;
            case DELETE:
                programaPanel = new ProgramaPanelDelete(handler, name + PanelMode.DELETE.toString());
                break;
            default:
                break;
        }

        return programaPanel;
    }

    protected void initUI(String title) {

        Box panel = Box.createVerticalBox();
        panel.add(getTitleBox(title));
        panel.add(getNombreBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getHorarioBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getValorSegundoAlAireBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getConductorBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getProductorBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(generateBotonera());
        panel.add(getjScrollPane());
        add(panel);

    }

    private Box getValorSegundoAlAireBox() {
        txtValorSegundoAlAire = new JTextField(columnSize);
        return crearCombo("Valor segundo al aire", txtValorSegundoAlAire);
    }

    private Box getHorarioBox() {
        txthorario = new JTextField(columnSize);
        return crearCombo("Horario", txthorario);
    }

    private Box getNombreBox() {
        txtNombre = new JTextField(columnSize);
        return crearCombo("Nombre", txtNombre);
    }

    protected abstract Box generateBotonera();

    protected void cleanJText() {
        txtNombre.setText(null);
        txthorario.setText(null);
        txtValorSegundoAlAire.setText(null);
        cmbProductor.setSelectedIndex(0);
        cmbCondutor.setSelectedIndex(0);
    }

    protected void enableEditControls(boolean var) {
        txthorario.setEnabled(var);
        txtValorSegundoAlAire.setEnabled(var);
        cmbCondutor.setEnabled(var);
        cmbProductor.setEnabled(var);
    }

    private Box getProductorBox() {
        cmbProductor = new JComboBox(handler.getProductoresVector());

        return crearCombo("Productor", cmbProductor);
    }

    private Box getConductorBox() {
        cmbCondutor = new JComboBox(handler.getConductoresVector());

        return crearCombo("Conductor", cmbCondutor);
    }

    protected Box getjScrollPane() {
        AuspiciantesTable auspiciantesTable = new AuspiciantesTable(handler.GetAuspiciantes());
        JTable tabla = new JTable(auspiciantesTable);
        JScrollPane scroll = new JScrollPane(tabla);

        Dimension listSize = new Dimension(250, 250);
        scroll.setSize(listSize);
        scroll.setMaximumSize(listSize);
        scroll.setPreferredSize(listSize);

        return crearCombo("Auspiciantes", scroll);
    }

}
