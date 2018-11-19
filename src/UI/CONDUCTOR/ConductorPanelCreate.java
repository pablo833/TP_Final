package UI.CONDUCTOR;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConductorPanelCreate extends ConductorPanel{
    public ConductorPanelCreate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    protected Box generateBotonera(){
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.createCondutor(createConductor());

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
