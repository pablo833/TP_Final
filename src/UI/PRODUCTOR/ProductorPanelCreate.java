package UI.PRODUCTOR;

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
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
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
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }
}
