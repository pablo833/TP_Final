package UI.PROGRAMA;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramaPanelCreate extends ProgramaPanel {
    public ProgramaPanelCreate(Handler handler, String title) {
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
                    handler.createPrograma(createPrograma());

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
              //  cleanJText();

            }
        });
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

}
