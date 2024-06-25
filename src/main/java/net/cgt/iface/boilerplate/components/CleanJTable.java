package net.cgt.iface.boilerplate.components;

import net.cgt.iface.boilerplate.graphics.Palette;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;


public class CleanJTable extends JTable {
    public CleanJTable(Vector<Vector<Object>> checkListData, Vector<Object> headers) {
        super(checkListData, headers);

        JTableHeader tableHeader = getTableHeader();
        TableColumnModel tableColumnModel = getColumnModel();
        CustomTableRenderer cellRenderer = new CustomTableRenderer(Palette.TEXT_WHITE.getColor());

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBorder(null);
        headerRenderer.setOpaque(false);
        headerRenderer.setForeground(Palette.TEXT_WHITE.getColor());
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        setRowHeight(35);
        setDragEnabled(false);
        setOpaque(false);
        setShowHorizontalLines(false);
        setShowVerticalLines(false);
        setFont(new Font("Inter Regular", Font.PLAIN, 14));
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setDefaultRenderer(Object.class, cellRenderer);
        setFillsViewportHeight(true);
        setEnabled(false);

        tableHeader.setDefaultRenderer(headerRenderer);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBackground(Palette.CLEAR.getColor());
        tableHeader.setForeground(Palette.TEXT_WHITE.getColor());

        for (int x = 0; x < tableColumnModel.getColumnCount(); x++) {
            tableColumnModel.getColumn(x).setPreferredWidth(80);
        }
    }
}
