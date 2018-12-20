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

public class ProgramaPanelDelete extends ProgramaPanel {

    private Programa programa;

    public ProgramaPanelDelete(Handler handler, String title) {
        super(handler, title);
    }

    @Override
    public Box generateBotonera() {

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.deletePrograma(createPrograma());
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
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
        enableEditControls(false);
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                programa = null;
                try {
                    programa = handler.getPrograma(txtNombre.getText());

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }

                if (programa != null) {
                    txthorario.setText(programa.getHorario());
                    txtValorSegundoAlAire.setText(programa.getValorSegundoAlAire().toString());

                    cmbProductor.setSelectedItem(programa.getConductor());
                    cmbCondutor.getModel().setSelectedItem(programa.getConductor());
                    cmbCondutor.setSelectedItem(programa.getConductor());
                    cmbProductor.getModel().setSelectedItem(programa.getProductor());
                    enableEditControls(true);
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPanel.showInformationMessage("Programa no encontrado.");
                }
            }
        });

        return btnFind;
    }

    private Programa createPrograma() {
        Programa newPrograma = null;

        if (programa != null) {
            newPrograma = new Programa(programa.getCodigo(),
                    txtNombre.getText(),
                    txthorario.getText(),
                    Double.valueOf(txtValorSegundoAlAire.getText()),
                    (Conductor) cmbCondutor.getSelectedItem(),
                    (Productor) cmbProductor.getSelectedItem());

        } else {
            newPrograma = new Programa(txtNombre.getText(),
                    txthorario.getText(),
                    Double.valueOf(txtValorSegundoAlAire.getText()),
                    (Conductor) cmbCondutor.getSelectedItem(),
                    (Productor) cmbProductor.getSelectedItem());

        }
        return newPrograma;
    }

}
