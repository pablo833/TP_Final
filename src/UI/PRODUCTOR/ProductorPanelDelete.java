package UI.PRODUCTOR;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductorPanelDelete extends ProductorPanel {
    protected ProductorPanelDelete(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    public Box generateBotonera() {
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.deleteProductor(createProductor());
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
            }
        });
        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }
}
