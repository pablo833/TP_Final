package UI.USER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

import UI.CustomOptionPane;
import UI.Handler;
import UI.USER.UserPanel;
import exceptions.RadioException;

public class UserPanelUpdate extends UserPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserPanelUpdate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {
        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    handler.updateUser(createUser());
                    btnOk.setEnabled(false);
                    cleanJText();
                } catch (RadioException e) {
                    CustomOptionPane.showErrorMessage(e.getMessage());
                }
            }
        });
        btnOk.setEnabled(false);

        botonera.add(btnOk);

        JButton btnFind = generateFindButton();

        botonera.add(btnFind);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

}
