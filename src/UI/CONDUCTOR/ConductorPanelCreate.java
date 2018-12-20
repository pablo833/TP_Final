package UI.CONDUCTOR;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConductorPanelCreate extends ConductorPanel {
    public ConductorPanelCreate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    if (COMMONS.Utils.isNumeric(txtDNI.getText())) {
                        handler.createCondutor(createConductor());
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
        Box botonera = crearOkBotonera(btnOk);

        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    private Conductor createConductor() {
        Conductor newConductor = null;

        newConductor = new Conductor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()), Double.valueOf(txtSueldo.getText()));

        return newConductor;
    }

}
