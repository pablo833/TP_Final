package UI.PROGRAMA;

import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramaPanelUpdate extends ProgramaPanel {
    public ProgramaPanelUpdate(Handler handler, String title) {
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
                        handler.updatePrograma(createPrograma());
                        btnOk.setEnabled(false);
                        cleanJText();
                    } else {
                        txtValorSegundoAlAire.grabFocus();
                        CustomOptionPane.showErrorMessage("El valor del segundo al aiere debe ser númerico");
                    }

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
