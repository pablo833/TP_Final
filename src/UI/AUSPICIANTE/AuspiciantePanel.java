package UI.AUSPICIANTE;

import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;
import UI.BasePanel;
import UI.CustomOptionPanel;
import UI.Handler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AuspiciantePanel extends BasePanel {

    protected Handler handler;

    private static final String name = "Auspiciantes ";

    protected final int horizontalStructureSize = 10;
    protected JTextField txtRazonSocial;
    protected JTextField txtCodigoRazonSocial;
    protected JButton btnOk;
    protected Auspiciante auspiciante = null;

    protected AuspiciantePanel() {

    }

    protected AuspiciantePanel(Handler handler, String title) {
        this.handler = handler;
        initUI(title);
    }

    public static AuspiciantePanel create(Handler handler, BasePanel.PanelMode action) {

        AuspiciantePanel auspiciantePanel = null;
        switch (action) {
            case CREATE:
                auspiciantePanel = new AuspiciantePanelCreate(handler, name + PanelMode.CREATE.toString());
                break;
            case UPDATE:
                auspiciantePanel = new AuspiciantePanelUpdate(handler, name + PanelMode.UPDATE.toString());
                break;
            case DELETE:
                auspiciantePanel = new AuspiciantePanelDelete(handler, name + PanelMode.DELETE.toString());
                break;
            default:
                break;
        }

        return auspiciantePanel;
    }

    private void initUI(String title) {

        Box panel = Box.createVerticalBox();
        panel.add(getTitleBox(title));
        panel.add(getAuspicianteBox());
        panel.add(getRazonSocialBox());
        panel.add(generateBotonera());
        enableEditControls(false);
        add(panel);
    }

    private Box getRazonSocialBox() {
        txtCodigoRazonSocial = new JTextField(columnSize);

        return crearCombo("ID Razón Social", txtCodigoRazonSocial);
    }

    private Box getAuspicianteBox() {
        txtRazonSocial = new JTextField(columnSize);
        return crearCombo("Razón Social", txtRazonSocial);
    }

    protected abstract Box generateBotonera();

    protected void cleanJText() {
        txtRazonSocial.setText(null);
        txtCodigoRazonSocial.setText(null);
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

    protected Auspiciante createAuspiciante() {
        Auspiciante newAuspiciante = null;

        if (auspiciante != null) {
            newAuspiciante = new Auspiciante(auspiciante.getCode(), txtRazonSocial.getText());
        } else {
            newAuspiciante = new Auspiciante(txtRazonSocial.getText());

        }
        return newAuspiciante;
    }

    protected void enableEditControls(boolean var) {
        txtCodigoRazonSocial.setEnabled(var);
    }
}

