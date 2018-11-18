package UI.AUSPICIANTE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.*;
import entidades.Auspiciante;
import exceptions.RadioException;

public abstract class AuspiciantePanel extends JPanel {

    protected Handler handler;

    protected final int horizontalStructureSize = 10;
    protected JTextField txtRazonSocial;
    protected JTextField txtCodigoRazonSocial;
    protected JButton btnOk;
    protected Auspiciante auspiciante = null;

    public static enum PanelMode {
        CREATE, UPDATE, DELETE;
    }

    protected AuspiciantePanel() {

    }

    protected AuspiciantePanel(Handler handler, String title) {
        this.handler = handler;
        initUI(title);
    }

    public static AuspiciantePanel create(Handler handler, AuspiciantePanel.PanelMode action) {

        AuspiciantePanel auspiciantePanel = null;
        switch (action) {
            case CREATE:
                auspiciantePanel = new AuspiciantePanelCreate(handler, PanelMode.CREATE.toString());
                break;
            case UPDATE:
                auspiciantePanel = new AuspiciantePanelUpdate(handler, PanelMode.UPDATE.toString());
                break;
            case DELETE:
                auspiciantePanel = new AuspiciantePanelDelete(handler, PanelMode.DELETE.toString());
                break;
            default:
                break;
        }

        return auspiciantePanel;
    }

    private void initUI(String title) {
        final int columnSize = 30;
        final int verticalStructureSize = 20;

        Box boxTitle = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        boxTitle.add(lblTitle);

        txtRazonSocial = new JTextField(columnSize);
        Box boxAuspiciante = crearCombo(horizontalStructureSize, "Razón Social", txtRazonSocial);

        txtCodigoRazonSocial = new JTextField(columnSize);
        enableEditControls(false);
        Box boxCodigoRazonSocial = crearCombo(horizontalStructureSize, "ID Razón Social", txtCodigoRazonSocial);

        Box botonera = generateBotonera();

        Box panel = Box.createVerticalBox();
        panel.add(boxTitle);
        panel.add(boxAuspiciante);
        panel.add(boxCodigoRazonSocial);
        panel.add(botonera);

        add(panel);
    }

    protected abstract Box generateBotonera();

    protected void cleanJText() {
        txtRazonSocial.setText(null);
        txtCodigoRazonSocial.setText(null);
    }

    private Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component) {
        Box boxUsername = Box.createHorizontalBox();
        JLabel lblUserName = new JLabel(labelText);
        boxUsername.add(lblUserName);
        boxUsername.add(Box.createHorizontalStrut(horizontalStructureSize));
        boxUsername.add(component);
        return boxUsername;
    }

    protected JButton generateFindButton() {

        JButton btnFind = new JButton("Buscar");
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                auspiciante = new Auspiciante(txtRazonSocial.getText());
                try {
                    auspiciante = handler.getAuspiciante(auspiciante);

                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }

                if (auspiciante != null) {
                    txtRazonSocial.setText(auspiciante.getRazonSocial());
                    txtCodigoRazonSocial.setText(String.valueOf(auspiciante.getCode()));
                    btnOk.setEnabled(true);
                } else {
                    CustomOptionPane.showInformationMessage("Auspiciante no encontrado.");
                }
            }
        });

        return btnFind;
    }

    protected Auspiciante createAuspiciante() {
        Auspiciante newAuspiciante = null;

        if (auspiciante != null) {
            newAuspiciante = new Auspiciante(auspiciante.getCode(), txtRazonSocial.getText());
        } else {
            newAuspiciante = new Auspiciante(txtRazonSocial.getText());

        }
        return newAuspiciante;
    }

    private void enableEditControls(boolean var) {
        txtCodigoRazonSocial.setEnabled(var);
    }
}
