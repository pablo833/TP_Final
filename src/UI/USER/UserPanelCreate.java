package UI.USER;

import javax.swing.*;
import UI.CustomOptionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.Handler;
import EXCEPTIONS.RadioException;

public class UserPanelCreate extends UserPanel {

    public UserPanelCreate(Handler handler, String title) {

        super(handler, title);
    }

    @Override
    protected Box generateBotonera() {

        Box botonera = Box.createHorizontalBox();
        botonera.add(Box.createHorizontalGlue());
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    handler.createUser(createUser());

                } catch (RadioException e) {
                    CustomOptionPanel.showErrorMessage(e.getMessage());
                }
                cleanJText();

            }
        });
        botonera.add(btnOk);

        botonera.add(Box.createHorizontalStrut(horizontalStructureSize));

        return botonera;
    }

}
