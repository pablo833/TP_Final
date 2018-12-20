package UI.PRODUCTOR;

import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductorPanelCreate extends ProductorPanel {
    protected ProductorPanelCreate(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    if (COMMONS.Utils.isNumeric(txtDNI.getText())) {
                        handler.createProductor(createProductor());
                        cleanJText();
                    } else {
                        txtDNI.grabFocus();
                        CustomOptionPanel.showErrorMessage("El DNI debe ser númerico");
                    }


                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
                cleanJText();

            }
        });
        Box botonera = crearOkBotonera(btnOk);
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }


    private Productor createProductor() {
        Productor newProdutor = null;

        newProdutor = new Productor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()));


        return newProdutor;
    }
}
