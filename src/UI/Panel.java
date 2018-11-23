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

    protected Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component) {
        Box box = Box.createHorizontalBox();
        JLabel lblUserName = new JLabel(labelText);
        box.add(lblUserName);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component);
        return box;
    }

    protected Box crearCombo(final int horizontalStructureSize, String labelText, JComponent component, JComponent component2) {
        Box box = Box.createHorizontalBox();
        JLabel lblUserName = new JLabel(labelText);
        box.add(lblUserName);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component);
        box.add(Box.createHorizontalStrut(horizontalStructureSize));
        box.add(component2);
        return box;
    }
}
