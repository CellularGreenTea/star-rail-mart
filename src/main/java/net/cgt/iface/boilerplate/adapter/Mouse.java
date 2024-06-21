package net.cgt.iface.boilerplate.adapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    private Color componentColor;

    public Mouse() {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component c = e.getComponent();
        c.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component c = e.getComponent();
        c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void setComponentColor(Color color) {componentColor = color;}

    public Color getComponentColor() {
        return componentColor;
    }

}
