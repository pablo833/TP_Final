package UI.AUSPICIANTE;

import javax.swing.*;
import UI.CustomOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.Handler;
import exceptions.RadioException;

public class AuspiciantePanelCreate extends AuspiciantePanel {

    public AuspiciantePanelCreate(Handler handler, String title) {

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
                    handler.createAuspiciante(createAuspiciante());

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
                cleanJText();

            }
        });
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }
}
