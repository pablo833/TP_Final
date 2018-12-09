package UI.PRODUCTOR;

import javax.swing.*;
import java.util.List;

import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;


public class ListProductoresPanel extends JPanel {
    Handler handler;

    public ListProductoresPanel(Handler handler) {
        this.handler = handler;
        try {
            initUI();
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }
    }

    private void initUI() throws RadioException {

        ProductorTable productoresTable = GetProductores();
        JTable tabla = new JTable(productoresTable);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll);
    }

    private ProductorTable GetProductores() throws RadioException {
        List<Productor> productores = handler.getProductores();
        return new ProductorTable(productores);

    }
}
