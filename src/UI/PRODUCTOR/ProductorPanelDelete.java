package UI.PRODUCTOR;

import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductorPanelDelete extends ProductorPanel {
    private Productor productor = null;

    protected ProductorPanelDelete(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    public Box generateBotonera() {

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.deleteProductor(createProductor());
                    btnOk.setEnabled(false);
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
            }
        });
        btnOk.setEnabled(false);

        Box botonera = crearOkBotonera(btnOk);

        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        enableEditControls(false);

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    private JButton generateFindButton() {

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

    private Productor createProductor() {
        Productor newProdutor = null;
        if (productor != null) {
            newProdutor = new Productor(productor.getCodigo(), productor.getNombre(), productor.getApellido(), productor.getDni());
        } else {
            newProdutor = new Productor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()));

        }
        return newProdutor;
    }
}
