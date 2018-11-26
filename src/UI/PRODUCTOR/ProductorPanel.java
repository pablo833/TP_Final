package UI.PRODUCTOR;

import ENTIDADES.Productor;
import UI.CustomOptionPane;
import UI.Handler;
import UI.basePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductorPanel extends basePanel {

    protected Handler handler;

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

    public static ProductorPanel create(Handler handler, basePanel.PanelMode action) {

        ProductorPanel productorPanel = null;
        switch (action) {
            case CREATE:
                productorPanel = new ProductorPanelCreate(handler, action.toString());
                break;
            case UPDATE:
                productorPanel = new ProductorPanelUpdate(handler, action.toString());
                break;
            case DELETE:
                productorPanel = new ProductorPanelDelete(handler, action.toString());
                break;
            default:
                break;
        }

        return productorPanel;
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


        Box botonera = generateBotonera();

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxUsername);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxApellido);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxDNI);
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

                productor = null;
                productor = handler.getProductor(Integer.valueOf(txtDNI.getText()));

                if (productor != null) {
                    txtNombre.setText(productor.getNombre());
                    txtApellido.setText(productor.getApellido());
                    txtDNI.setText(String.valueOf(productor.getDni()));

                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPane.showInformationMessage("Productor no encontrado.");
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
