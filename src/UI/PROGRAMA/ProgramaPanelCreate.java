package UI.PROGRAMA;

import ENTIDADES.Conductor;
import ENTIDADES.Productor;
import ENTIDADES.Programa;
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

        Box botonera = crearOkBotonera(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    private Programa createPrograma() {
        Programa newPrograma = null;


        newPrograma = new Programa(txtNombre.getText(),
                txthorario.getText(),
                Double.valueOf(txtValorSegundoAlAire.getText()),
                (Conductor) cmbCondutor.getSelectedItem(),
                (Productor) cmbProductor.getSelectedItem());


        return newPrograma;
    }

    @Override
    protected Box getjScrollPane() {
        Box box = Box.createHorizontalBox();
        return box;

    }


}
