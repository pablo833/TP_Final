package UI.CONDUCTOR;

import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConductorPanelUpdate extends ConductorPanel {

    protected Conductor conductor = null;

    public ConductorPanelUpdate(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {

        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (COMMONS.Utils.isNumeric(txtDNI.getText())) {
                        handler.updateConductor(createConductor());
                        btnOk.setEnabled(false);
                        cleanJText();

                    } else {
                        txtDNI.grabFocus();
                        CustomOptionPanel.showErrorMessage("El DNI debe ser númerico");
                    }

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
            }
        });
        btnOk.setEnabled(false);

        Box botonera = crearOkBotonera(btnOk);

        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }


    private JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        enableEditControls(false);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                conductor = null;
                try {
                    conductor = handler.getConductor(Integer.valueOf(txtDNI.getText()));

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (conductor != null) {
                    txtNombre.setText(conductor.getNombre());
                    txtApellido.setText(conductor.getApellido());
                    txtDNI.setText(String.valueOf(conductor.getDni()));
                    txtSueldo.setText(String.valueOf(conductor.getSueldo()));
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Conductor no encontrado.");
                }
            }
        });

        return btnFind;
    }

    private Conductor createConductor() {
        Conductor newConductor = null;
        if (conductor != null) {
            newConductor = new Conductor(conductor.getCodigo(), conductor.getNombre(), conductor.getApellido(), conductor.getDni(), conductor.getSueldo());
        } else {
            newConductor = new Conductor(txtNombre.getText(), txtApellido.getText(), Integer.valueOf(txtDNI.getText()), Double.valueOf(txtSueldo.getText()));

        }
        return newConductor;
    }
}
