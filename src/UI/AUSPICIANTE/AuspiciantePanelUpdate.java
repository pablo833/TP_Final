package UI.AUSPICIANTE;

import UI.CustomOptionPane;
import UI.Handler;
import exceptions.RadioException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuspiciantePanelUpdate extends AuspiciantePanel {

    public AuspiciantePanelUpdate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    public Box generateBotonera() {

        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    handler.updateAuspiciante(createAuspiciante());
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
