package UI.PRODUCTOR;

import ENTIDADES.Productor;
import UI.BasePanel;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ProductorPanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Productores ";

    protected JTextField txtNombre;
    protected JTextField txtApellido;
    protected JTextField txtDNI;
    protected JButton btnOk;
    protected Productor productor = null;

    protected ProductorPanel(Handler handler, String title) {
        super();
        this.handler = handler;
        unitUI(title);
    }

    public static ProductorPanel create(Handler handler, BasePanel.PanelMode action) {

        ProductorPanel productorPanel = null;
        switch (action) {
            case CREATE:
                productorPanel = new ProductorPanelCreate(handler, name + action.toString());
                break;
            case UPDATE:
                productorPanel = new ProductorPanelUpdate(handler, name + action.toString());
                break;
            case DELETE:
                productorPanel = new ProductorPanelDelete(handler, name + action.toString());
                break;
            default:
                break;
        }

        return productorPanel;
    }

    private void unitUI(String title) {

        Box panel = Box.createVerticalBox();
        panel.add(getTitleBox(title));
        panel.add(getNombreBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getApellidoBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getDNIBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(generateBotonera());

        add(panel);

    }

    private Box getDNIBox() {
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

                productor = null;
                if (COMMONS.Utils.isNumeric(txtDNI.getText())) {
                    productor = handler.getProductor(Integer.valueOf(txtDNI.getText()));
                    if (productor != null) {
                        txtNombre.setText(productor.getNombre());
                        txtApellido.setText(productor.getApellido());
                        txtDNI.setText(String.valueOf(productor.getDni()));

                        enableEditControls(true);
                        btnOk.setEnabled(true);
                    } else {
                        CustomOptionPanel.showInformationMessage("Productor no encontrado.");
                    }
                } else {
                    txtDNI.grabFocus();
                    CustomOptionPanel.showErrorMessage("El DNI debe ser númerico");
                }
            }
        });

        return btnFind;
    }

    protected void cleanJText() {
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtDNI.setText(null);
    }

    protected void enableEditControls(boolean var) {
        txtNombre.setEnabled(var);
        txtApellido.setEnabled(var);
    }

    protected Productor createProductor() {
        Productor newProdutor = null;
        if (productor != null) {
            newProdutor = new Productor(productor.getCodigo(), productor.getNombre(), productor.getApellido(), productor.getDni());
        } else {
            newProdutor = new Productor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()));

        }
        return newProdutor;
    }
}
