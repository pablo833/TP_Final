package UI.PROGRAMA;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
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
                    if (COMMONS.Utils.isNumeric(txtValorSegundoAlAire.getText())) {
                        handler.createPrograma(createPrograma());
                        cleanJText();
                    } else {
                        txtValorSegundoAlAire.grabFocus();
                        CustomOptionPanel.showErrorMessage("El valor del segundo al aire debe ser númerico");
                    }
                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
            }
        });
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    @Override
    protected Box getjScrollPane() {
        Box box = Box.createHorizontalBox();
        return box;

    }


}
