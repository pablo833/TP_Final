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

}
