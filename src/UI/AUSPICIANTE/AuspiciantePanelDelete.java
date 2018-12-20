package UI.AUSPICIANTE;

import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuspiciantePanelDelete extends AuspiciantePanel {

    private Auspiciante auspiciante = null;

    public AuspiciantePanelDelete(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    public Box generateBotonera() {

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {


            }
        });

        btnOk.setEnabled(false);
        Box botonera = crearOkBotonera(btnOk);
        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        enableEditControls(false);

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    private JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                auspiciante = new Auspiciante(txtRazonSocial.getText());
                try {
                    auspiciante = handler.getAuspiciante(auspiciante);

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (auspiciante != null) {
                    txtRazonSocial.setText(auspiciante.getRazonSocial());
                    txtCodigoRazonSocial.setText(String.valueOf(auspiciante.getCode()));
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Auspiciante no encontrado.");
                }
            }
        });

        return btnFind;
    }

    private Auspiciante createAuspiciante() {
        Auspiciante newAuspiciante = null;

        if (auspiciante != null) {
            newAuspiciante = new Auspiciante(auspiciante.getCode(), txtRazonSocial.getText());
        } else {
            newAuspiciante = new Auspiciante(txtRazonSocial.getText());

        }
        return newAuspiciante;
    }

}
