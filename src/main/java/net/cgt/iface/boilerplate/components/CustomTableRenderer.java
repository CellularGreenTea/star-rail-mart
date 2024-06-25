package net.cgt.iface.boilerplate.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableRenderer extends DefaultTableCellRenderer {
    private final Color cellForeground;

    public CustomTableRenderer(Color cellForeground) {
        super();
        this.cellForeground = cellForeground;

        setOpaque(false);
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBorder(noFocusBorder);
        setForeground(cellForeground);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
