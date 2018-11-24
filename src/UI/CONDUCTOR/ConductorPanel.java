package UI.CONDUCTOR;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;
import UI.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConductorPanel extends Panel {

    protected Handler handler;

    protected JTextField txtNombre;
    protected JTextField txtApellido;
    protected JTextField txtDNI;
    protected JTextField txtSueldo;
    protected JButton btnOk;
    protected Conductor conductor = null;

    protected ConductorPanel() {
    }

    protected ConductorPanel(Handler handler, String title) {
        super();
        this.handler = handler;
        unitUI(title);
    }

    public static ConductorPanel create(Handler handler, Panel.PanelMode action) {

        ConductorPanel conductorPanel = null;
        switch (action) {
            case CREATE:
                conductorPanel = new ConductorPanelCreate(handler, action.toString());
                break;
            case UPDATE:
                conductorPanel = new ConductorPanelUpdate(handler, action.toString());
                break;
            case DELETE:
                conductorPanel = new ConductorPanelDelete(handler, action.toString());
                break;
            default:
                break;
        }

        return conductorPanel;
    }

    private void unitUI(String title) {

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        txtNombre = new JTextField(columnSize);
        Box boxUsername = crearCombo("Nombre", txtNombre);
        txtApellido = new JTextField(columnSize);
        Box boxApellido = crearCombo("Apellido", txtApellido);
        txtDNI = new JTextField(columnSize);
        Box boxDNI = crearCombo("DNI", txtDNI);
        txtSueldo = new JTextField(columnSize);
        Box boxSueldo = crearCombo("Sueldo", txtSueldo);

        Box botonera = generateBotonera();

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxUsername);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxApellido);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxDNI);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxSueldo);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(botonera);

        add(panel);

    }

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

                conductor = null;
                try {
                    conductor = handler.getConductor(Integer.valueOf(txtDNI.getText()));

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }

                if (conductor != null) {
                    txtNombre.setText(conductor.getNombre());
                    txtApellido.setText(conductor.getApellido());
                    txtDNI.setText(String.valueOf(conductor.getDni()));
                    txtSueldo.setText(String.valueOf(conductor.getSueldo()));
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPane.showInformationMessage("Conductor no encontrado.");
                }
            }
        });

        return btnFind;
    }

    protected void cleanJText() {
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtSueldo.setText(null);
        txtDNI.setText(null);
    }

    protected void enableEditControls(boolean var) {
        txtNombre.setEnabled(var);
        txtApellido.setEnabled(var);
        txtSueldo.setEnabled(var);
    }

    protected Conductor createConductor() {
        Conductor newConductor = null;
        if (conductor != null) {
            newConductor = new Conductor(conductor.getCodigo(), conductor.getNombre(), conductor.getApellido(), conductor.getDni(), conductor.getSueldo());
        } else {
            newConductor = new Conductor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()), Double.valueOf(txtSueldo.getText()));

        }
        return newConductor;
    }
}
