package UI.PROGRAMA;

import ENTIDADES.Auspiciante;
import ENTIDADES.Contrato;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramaAuspiciantePanel extends ProgramaPanel {
    public ProgramaAuspiciantePanel(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {
        Box botonera = Box.createHorizontalBox();

        JButton btnFind = generateFindButton();

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

    @Override
    protected Box generateAuspicianteBox() {

        btnAddAuspiciante = new JButton("Add");
        enableEditControls(false);

        btnAddAuspiciante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Contrato contrato = new Contrato(programa.getCodigo(),
                            ((Auspiciante) cmbAuspiciantes.getSelectedItem()).getCode(), 1);

                    handler.createContrato(contrato);

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
            }
        });
        return getAuspicianteBox();
    }

}
