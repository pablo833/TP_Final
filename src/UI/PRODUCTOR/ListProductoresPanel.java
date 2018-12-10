package UI.PRODUCTOR;

import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;
import UI.CustomOptionPanel;
import UI.Handler;
import UI.ListEntitiesPanel;

import javax.swing.*;
import java.util.List;


public class ListProductoresPanel extends ListEntitiesPanel {

    private static final String name = "Productores";

    public ListProductoresPanel(Handler handler) {
        super(handler);
        initUI();
    }

    protected void initUI() {

        ProductorTable productoresTable = null;
        add(getTitleBox(name));
        try {
            productoresTable = GetProductores();
            JTable tabla = new JTable(productoresTable);
            JScrollPane scroll = new JScrollPane(tabla);
            add(scroll);
        } catch (RadioException e) {
            CustomOptionPanel.showErrorMessage(e.getMessage());
        }

    }

    private ProductorTable GetProductores() throws RadioException {
        List<Productor> productores = handler.getProductores();
        return new ProductorTable(productores);

    }
}
