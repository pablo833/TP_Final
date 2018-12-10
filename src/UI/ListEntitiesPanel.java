package UI;

import javax.swing.*;

public abstract class ListEntitiesPanel extends JPanel {
    protected Handler handler;
    protected final int verticalStructureSize = 20;

    public ListEntitiesPanel(Handler handler) {
        this.handler = handler;
    }

    protected Box getTitleBox(String title) {
        Box panel = Box.createHorizontalBox();
        JLabel lblTitle = new JLabel(title);
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(verticalStructureSize));
        return panel;
    }

    protected abstract void initUI();
}
