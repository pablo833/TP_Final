package UI.PROGRAMA;

import ENTIDADES.Auspiciante;
import ENTIDADES.Conductor;
import ENTIDADES.Productor;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;
import UI.AUSPICIANTE.AuspiciantesTable;
import UI.CustomOptionPane;
import UI.Handler;
import UI.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ProgramaPanel extends Panel {
    protected Handler handler;

    protected JTextField txtNombre;
    protected JTextField txthorario;
    protected JTextField txtValorSegundoAlAire;
    protected JComboBox cmbCondutor;
    protected JComboBox cmbProductor;
    protected JButton btnOk;
    protected Programa programa;
    protected JComboBox cmbAuspiciantes;
    protected JButton btnAddAuspiciante;

    protected ProgramaPanel() {

    }

    protected ProgramaPanel(Handler handler, String title) {
        this.handler = handler;
        initUI(title);
    }

    public static ProgramaPanel create(Handler handler, Panel.PanelMode action) {
        ProgramaPanel programaPanel = null;
        switch (action) {
            case CREATE:
                programaPanel = new ProgramaPanelCreate(handler, PanelMode.CREATE.toString());
                break;
            case UPDATE:
                programaPanel = new ProgramaPanelUpdate(handler, PanelMode.UPDATE.toString());
                break;
            case DELETE:
                programaPanel = new ProgramaPanelDelete(handler, PanelMode.DELETE.toString());
                break;
            default:
                break;
        }

        return programaPanel;
    }

    private void initUI(String title) {

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        txtNombre = new JTextField(columnSize);
        Box boxNombre = crearCombo(horizontalStructureSize, "Nombre", txtNombre);

        txthorario = new JTextField(columnSize);
        Box boxHorario = crearCombo(horizontalStructureSize, "Horario", txthorario);

        txtValorSegundoAlAire = new JTextField(columnSize);
        Box boxValorSegundoAlAire = crearCombo(horizontalStructureSize, "Valor segundo al aire", txtValorSegundoAlAire);

        Box boxConductor = getConductorBox();

        Box boxProductor = getProductorBox();


        Box boxAuspiciante = generateAuspicianteBox();

        Box botonera = generateBotonera();

        Box boxScroll = getjScrollPane();

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxNombre);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxHorario);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxValorSegundoAlAire);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxConductor);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxProductor);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxAuspiciante);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(botonera);

        add(panel);
        add(boxScroll);

    }

    protected Box generateAuspicianteBox(){ return null;}

    @Override
    protected Box generateBotonera() {
        return null;
    }

    protected JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        enableEditControls(false);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                programa = null;
                try {
                    programa = handler.getPrograma(txtNombre.getText());

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }

                if (programa != null) {
                    txthorario.setText(programa.getHorario());
                    txtValorSegundoAlAire.setText(programa.getValorSegundoAlAire().toString());

                    cmbProductor.setSelectedItem(programa.getConductor());
                    cmbCondutor.getModel().setSelectedItem(programa.getConductor());
                    cmbCondutor.setSelectedItem(programa.getConductor());
                    cmbProductor.getModel().setSelectedItem(programa.getProductor());
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPane.showInformationMessage("Programa no encontrado.");
                }
            }
        });

        return btnFind;
    }

    protected Programa createPrograma() {
        Programa newPrograma = null;

        if (programa != null) {
            newPrograma = new Programa(programa.getCodigo(),
                    txtNombre.getText(),
                    txthorario.getText(),
                    Double.valueOf(txtValorSegundoAlAire.getText()),
                    (Conductor) cmbCondutor.getSelectedItem(),
                    (Productor) cmbProductor.getSelectedItem());

        } else {
            newPrograma = new Programa(txtNombre.getText(),
                    txthorario.getText(),
                    Double.valueOf(txtValorSegundoAlAire.getText()),
                    (Conductor) cmbCondutor.getSelectedItem(),
                    (Productor) cmbProductor.getSelectedItem());

        }
        return newPrograma;
    }

    protected void cleanJText() {
        txtNombre.setText(null);
        txthorario.setText(null);
        txtValorSegundoAlAire.setText(null);
        cmbProductor.setSelectedIndex(0);
        cmbCondutor.setSelectedIndex(0);
        cmbAuspiciantes.setSelectedIndex(0);
    }

    protected void enableEditControls(boolean var) {
        txthorario.setEnabled(var);
        txtValorSegundoAlAire.setEnabled(var);
        cmbCondutor.setEnabled(var);
        cmbProductor.setEnabled(var);
    }

    private AuspiciantesTable GetAuspiciantes() {
        List<Auspiciante> auspiciantes = null;
        try {
            auspiciantes = handler.GetAuspiciantes();
        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return new AuspiciantesTable(auspiciantes);
    }

    private Box getProductorBox() {
        cmbProductor = new JComboBox(getProductorVector());

        return crearCombo(horizontalStructureSize, "Productor", cmbProductor);
    }

    private Box getConductorBox() {
        cmbCondutor = new JComboBox(getConductorVector());

        return crearCombo(horizontalStructureSize, "Conductor", cmbCondutor);
    }

    protected Box getAuspicianteBox() {
        cmbAuspiciantes = new JComboBox(getAuspicianteVector());

        return crearCombo(horizontalStructureSize, "Auspiciante", cmbAuspiciantes, btnAddAuspiciante);
    }

    private Vector getProductorVector() {
        Vector productorModel = new Vector();

        try {
            productorModel = handler.getProductoresVector();

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return productorModel;
    }

    private Vector getConductorVector() {
        Vector conductorModel = new Vector();

        try {
            conductorModel = handler.getConductoresVector();

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return conductorModel;
    }

    private Vector getAuspicianteVector() {
        Vector auspicianteModel = new Vector();

        try {
            auspicianteModel = handler.getAuspicianteVector();

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return auspicianteModel;
    }

    private Box getjScrollPane() {
        AuspiciantesTable auspiciantesTable = GetAuspiciantes();
        JTable tabla = new JTable(auspiciantesTable);
        JScrollPane scroll = new JScrollPane(tabla);

        Dimension listSize = new Dimension(250, 250);
        scroll.setSize(listSize);
        scroll.setMaximumSize(listSize);
        scroll.setPreferredSize(listSize);

        return crearCombo(horizontalStructureSize, "Auspiciantes", scroll);
    }

}
