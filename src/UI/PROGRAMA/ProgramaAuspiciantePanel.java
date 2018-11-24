package UI.PROGRAMA;

import ENTIDADES.Auspiciante;
import ENTIDADES.Contrato;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;
import UI.AUSPICIANTE.AuspiciantesTable;
import UI.CustomOptionPane;
import UI.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ProgramaAuspiciantePanel extends ProgramaPanel {
    private Handler handler;
    private JComboBox cmbPrograma;
    protected JComboBox cmbAuspiciantes;
    protected JButton btnAddAuspiciante;

    public ProgramaAuspiciantePanel(Handler handler) {
        this.handler = handler;
        initUI("Contratos");
    }

    @Override
    protected void initUI(String title) {

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        Box boxPrograma = getProgramaBox();

        JTextField txtNombre = new JTextField(columnSize);
        Box boxNombre = crearCombo("Nombre", txtNombre);
        txtNombre.setEnabled(false);

        JTextField txtConductor = new JTextField(columnSize / 2);
        //  Box boxConductor = crearCombo(horizontalStructureSize, "Conductor", txtConductor);
        txtConductor.setEnabled(false);

        JTextField txtProductor = new JTextField(columnSize / 2);
        Box boxProductor = crearCombo("Conductor", txtConductor, "Productor", txtProductor);
        txtProductor.setEnabled(false);

        JTextField txthorario = new JTextField(columnSize / 2);
        // Box boxHorario = crearCombo(horizontalStructureSize, "Horario", txthorario);
        txthorario.setEnabled(false);

        JTextField txtValorSegundoAlAire = new JTextField(columnSize / 2);
        Box boxValorSegundoAlAire = crearCombo("Horario", txthorario, "Valor segundo al aire", txtValorSegundoAlAire);
        txtValorSegundoAlAire.setEnabled(false);

        JTextField txtTiempoDePauta = new JTextField(columnSize);
        Box boxTiempDePauta = crearCombo("Tiempo de pauta", txtTiempoDePauta);

        Box boxAuspiciante = generateAuspicianteBox();

        Box boxScroll = generatejScrollPane(null);

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxPrograma);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxNombre);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        //  panel.add(boxConductor);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxProductor);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        //panel.add(boxHorario);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxValorSegundoAlAire);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxTiempDePauta);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(boxAuspiciante);
        panel.add(Box.createVerticalStrut(verticalStructureSize));

        panel.add(boxScroll);
        add(panel);

    }

    private Box getProgramaBox() {
        cmbPrograma = new JComboBox(getProgramaVector());

        cmbPrograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //doSomething();
            }
        });
        return crearCombo("Programa", cmbPrograma);
    }

    private Vector getProgramaVector() {
        Vector programaModel = new Vector();

        try {
            programaModel = handler.getProgramasVector();

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return programaModel;
    }


    private Box generateAuspicianteBox() {

        btnAddAuspiciante = new JButton("Add");

        btnAddAuspiciante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Contrato contrato = new Contrato(programa,
                            (Auspiciante) cmbAuspiciantes.getSelectedItem(),
                            1);

                    handler.createContrato(contrato);

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
            }
        });
        return getAuspicianteBox();
    }

    private Box getAuspicianteBox() {
        btnAddAuspiciante = new JButton("Add");
        cmbAuspiciantes = new JComboBox(getAuspicianteVector());
        return crearCombo("Auspiciante", cmbAuspiciantes, btnAddAuspiciante);
    }


    private Vector getAuspicianteVector() {
        Vector auspicianteModel = new Vector();

        try {
            auspicianteModel = handler.getAuspicianteVector();

        } catch (RadioException e) {
            CustomOptionPane.showErrorMessage(e.getMessage());
        }

        return auspicianteModel;
    }


    protected Box generatejScrollPane(Programa programa) {
        if (programa != null) {
            AuspiciantesTable auspiciantesTable = GetAuspiciantes();
            JTable tabla = new JTable(auspiciantesTable);
            JScrollPane scroll = new JScrollPane(tabla);

            Dimension listSize = new Dimension(250, 250);
            scroll.setSize(listSize);
            scroll.setMaximumSize(listSize);
            scroll.setPreferredSize(listSize);

            return crearCombo("Auspiciantes", scroll);
        } else {
            Box box = Box.createHorizontalBox();
            return box;
        }
    }


}
