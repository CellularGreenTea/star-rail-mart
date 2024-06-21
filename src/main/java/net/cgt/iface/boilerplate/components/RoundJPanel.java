package net.cgt.iface.boilerplate.components;

import javax.swing.*;
import java.awt.*;

public class RoundJPanel extends JPanel {
    private final int radius;
    private final boolean border;
    private Color color;
    private float alpha = 0.0F;

    public RoundJPanel(Color color, int radius, boolean border) {
        setOpaque(false);

        this.color = color;
        this.radius = radius;
        this.border = border;
    }

    public RoundJPanel(Color color, float alpha, int radius, boolean border) {
        setOpaque(false);

        this.color = color;
        this.alpha = alpha;
        this.radius = radius;
        this.border = border;
    }

    public void setBackgroundColor(Color color) {
        this.color = color;
        repaint();
    }

    public Color getBackgroundColor() {
        return this.color;
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        int width = getWidth();
        int height = getHeight();
        Graphics2D graphic2d = (Graphics2D) graphic.create();

        graphic2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (alpha != 0.0F) {
            graphic2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }

        graphic2d.setColor(color);
        graphic2d.fillRoundRect(0, 0, width - 1, height - 1, radius, radius);

        if (border) {
            graphic2d.setColor(getForeground());
            graphic2d.drawRoundRect(0, 0, width - 1, height - 1, radius, radius);
        }
    }

    @Override
    public void paintChildren(Graphics graphic) {
        if (alpha != 0.0F) {
            Graphics2D graphic2d = (Graphics2D) graphic.create();
            super.paintChildren(graphic2d);
            graphic2d.dispose();
        }
        else {
            super.paintChildren(graphic);
        }
    }
}