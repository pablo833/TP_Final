package UI.PROGRAMA;

import ENTIDADES.Auspiciante;
import ENTIDADES.Contrato;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;
import UI.BasePanel;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramaAuspiciantePanel extends BasePanel {
    private Handler handler;
    private JComboBox cmbPrograma;
    private JComboBox cmbAuspiciantes;
    private JButton btnAddAuspiciante;
    private JButton btnRemoveAuspiciante;
    private JTextField txtNombre;
    private JTextField txtConductor;
    private JTextField txtProductor;
    private JTextField txthorario;
    private JTextField txtValorSegundoAlAire;
    private JTextField txtTiempoDePauta;
    private JScrollPane scroll;
    private JTable tabla;
    private ContratoTable contratosTable;

    public ProgramaAuspiciantePanel(Handler handler) {
        this.handler = handler;
        initUI("Contratos");
    }

    protected void initUI(String title) {

        Box panel = Box.createVerticalBox();
        panel.add(panel.add(getTitleBox(title)));
        panel.add(getProgramaBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getNombreBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getConductorProductorBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getHorarioValorSegundoAlaireBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(getTiempoDePautaBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        panel.add(generateAuspicianteBox());
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        enableAddAuspicianete(false);

        panel.add(initaliceScrollPane());
        panel.add(generateBotonera());
        add(panel);

    }

    private Box getNombreBox() {
        txtNombre = new JTextField(columnSize);
        Box boxNombre = crearCombo("Nombre", txtNombre);
        txtNombre.setEnabled(false);
        return boxNombre;
    }

    private Box getTiempoDePautaBox() {
        txtTiempoDePauta = new JTextField(columnSize);
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        return crearCombo("Tiempo de pauta", txtTiempoDePauta);
    }

    private Box getHorarioValorSegundoAlaireBox() {
        txthorario = new JTextField(columnSize / 2);
        txthorario.setEnabled(false);

        txtValorSegundoAlAire = new JTextField(columnSize / 2);
        Box boxValorSegundoAlAire = crearCombo("Horario", txthorario, "Valor segundo al aire", txtValorSegundoAlAire);
        txtValorSegundoAlAire.setEnabled(false);
        return boxValorSegundoAlAire;
    }

    private Box getConductorProductorBox() {
        txtConductor = new JTextField(columnSize / 2);
        txtConductor.setEnabled(false);

        txtProductor = new JTextField(columnSize / 2);
        Box boxProductor = crearCombo("Conductor", txtConductor, "Productor", txtProductor);
        txtProductor.setEnabled(false);
        return boxProductor;
    }

    private Box getProgramaBox() {
        cmbPrograma = new JComboBox(getProgramaVector());

        cmbPrograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setData((Programa) cmbPrograma.getSelectedItem());
            }

            private void setData(Programa programa) {
                txtNombre.setText(programa.getNombre());
                txtConductor.setText(programa.getConductor().toString());
                txtProductor.setText(programa.getProductor().toString());
                txthorario.setText(programa.getHorario());
                txtValorSegundoAlAire.setText(programa.getValorSegundoAlAire().toString());
                enableAddAuspicianete(true);
                updatejScrollPane(programa);
                if (tabla.getRowCount() > 0) {
                    btnRemoveAuspiciante.setEnabled(true);
                }
            }
        });
        return crearCombo("Programa", cmbPrograma);
    }

    private Vector getProgramaVector() {
        Vector programaModel = new Vector();

        try {
            programaModel = handler.getProgramasVector();

        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }

        return programaModel;
    }

    private Box generateAuspicianteBox() {

        btnAddAuspiciante = new JButton("Add");

        btnAddAuspiciante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {

                    if (COMMONS.Utils.isNumeric(txtTiempoDePauta.getText())) {
                        Contrato contrato = new Contrato((Programa) cmbPrograma.getSelectedItem(),
                                (Auspiciante) cmbAuspiciantes.getSelectedItem(),
                                Integer.valueOf(txtTiempoDePauta.getText()));

                        handler.createContrato(contrato);
                        CustomOptionPanel.showInformationMessage("Operación exitosa!");
                        updatejScrollPane((Programa) cmbPrograma.getSelectedItem());
                    } else {
                        txtTiempoDePauta.grabFocus();
                        CustomOptionPanel.showErrorMessage("El tiempo de la pauta debe ser un número.");
                    }
                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
            }
        });
        return getAuspicianteBox();
    }

    private Box getAuspicianteBox() {
        cmbAuspiciantes = new JComboBox(getAuspicianteVector());
        return crearCombo("Auspiciante", cmbAuspiciantes, btnAddAuspiciante);
    }

    private Vector getAuspicianteVector() {
        Vector auspicianteModel = new Vector();

        try {
            auspicianteModel = handler.getAuspicianteVector();

        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }

        return auspicianteModel;
    }

    protected void updatejScrollPane(Programa programa) {
        if (programa != null) {

            try {

                contratosTable = GetContratos(programa);
                tabla.setModel(contratosTable);
                scroll.setViewportView(tabla);


            } catch (RadioException e) {
                CustomOptionPanel.showErrorMessage("Hubo un problema en la carga de Auspiciantes.");
            }
        }
    }

    private Box initaliceScrollPane() {
        List<Contrato> contratos = new ArrayList<Contrato>();
        tabla = new JTable(new ContratoTable(contratos));
        scroll = new JScrollPane(tabla);

        Dimension listSize = new Dimension(250, 250);
        scroll.setSize(listSize);
        scroll.setMaximumSize(listSize);
        scroll.setPreferredSize(listSize);
        return crearCombo("Auspiciantes", scroll);
    }

    private ContratoTable GetContratos(Programa programa) throws RadioException {
        List<Contrato> contratos = null;

        contratos = handler.GetContratos(programa);
        return new ContratoTable(contratos);
    }

    private void enableAddAuspicianete(boolean var) {
        cmbAuspiciantes.setEnabled(var);
        btnAddAuspiciante.setEnabled(var);
    }

    protected Box generateBotonera() {

        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        btnRemoveAuspiciante = new JButton("Remove");
        btnRemoveAuspiciante.setEnabled(false);
        btnRemoveAuspiciante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (tabla.getSelectedRow() >= 0) {
                    Contrato contrato = contratosTable.getSelectedElement(tabla.getSelectedRow());
                    handler.deleteContract(contrato);
                    updatejScrollPane((Programa) cmbPrograma.getSelectedItem());
                } else {
                    CustomOptionPanel.showInformationMessage("Debe seleccionar un contrato de la lista.");
                }
            }
        });
        botonera.add(btnRemoveAuspiciante);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }
}
