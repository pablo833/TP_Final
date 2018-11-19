package UI.PRODUCTOR;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductorPanelUpdate extends ProductorPanel {
    protected ProductorPanelUpdate(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    handler.updateCProductor(createProductor());
                    btnOk.setEnabled(false);
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
            }
        });
        btnOk.setEnabled(false);

        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }
}
