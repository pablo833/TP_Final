package UI;

import javax.swing.*;

public abstract class Panel extends JPanel {

    protected final int columnSize = 30;
    protected final int verticalStructureSize = 20;
    protected final int horizontalStructureSize = 10;

    public enum PanelMode {
        CREATE, UPDATE, DELETE
    }

    protected abstract Box generateBotonera();

    protected Box crearCombo(String labelText, JComponent component) {
        Box box = Box.createHorizontalBox();
        JLabel lblDescriptio = new JLabel(labelText);
        box.add(lblDescriptio);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component);
        return box;
    }

    protected Box crearCombo(String labelText, JComponent component, JComponent component2) {
        Box box = Box.createHorizontalBox();
        JLabel lblDescription = new JLabel(labelText);
        box.add(lblDescription);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component2);
        return box;
    }

    protected Box crearCombo(String labelText, JComponent component, String labelText2, JComponent component2) {
        Box box = Box.createHorizontalBox();
        JLabel lblDescription = new JLabel(labelText);
        box.add(lblDescription);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        JLabel lblDescription2 = new JLabel(labelText2);
        box.add(lblDescription2);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component2);
        return box;
    }
}
