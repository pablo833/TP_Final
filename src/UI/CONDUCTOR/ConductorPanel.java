package UI.CONDUCTOR;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.BasePanel;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ConductorPanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Conductores ";

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

    public static ConductorPanel create(Handler handler, BasePanel.PanelMode action) {

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

        Box panel = Box.createVerticalBox();
        panel.add(getTitleBox(title));
        panel.add(getNombreBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getApellidoBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getDBIBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getSueldoBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(generateBotonera());

        add(panel);

    }

    private Box getSueldoBox() {
        txtSueldo = new JTextField(columnSize);
        return crearCombo("Sueldo", txtSueldo);
    }

    private Box getDBIBox() {
        txtDNI = new JTextField(columnSize);
        return crearCombo("DNI", txtDNI);
    }

    private Box getApellidoBox() {
        txtApellido = new JTextField(columnSize);
        return crearCombo("Apellido", txtApellido);
    }

    private Box getNombreBox() {
        txtNombre = new JTextField(columnSize);
        return crearCombo("Nombre", txtNombre);
    }

    protected abstract Box generateBotonera();

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
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (conductor != null) {
                    txtNombre.setText(conductor.getNombre());
                    txtApellido.setText(conductor.getApellido());
                    txtDNI.setText(String.valueOf(conductor.getDni()));
                    txtSueldo.setText(String.valueOf(conductor.getSueldo()));
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Conductor no encontrado.");
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
