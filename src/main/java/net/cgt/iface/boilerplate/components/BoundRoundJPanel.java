package net.cgt.iface.boilerplate.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class BoundRoundJPanel extends JPanel {
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    public BoundRoundJPanel() {
        setOpaque(false);
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        Graphics2D graphic2d = (Graphics2D) graphic.create();
        graphic2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphic2d.setColor(getBackground());

        Area area = new Area(createRoundTopLeft());

        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }

        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }

        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }

        graphic2d.fill(area);
        graphic2d.dispose();

        super.paintComponent(graphic);
    }

    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double((double) roundX / 2, 0, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, (double) roundY / 2, width, height - (double) roundY / 2)));
        return area;
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, (double) roundY / 2, width, height - (double) roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double((double) roundX / 2, 0, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - (double) roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - (double) roundY / 2)));
        return area;
    }
}

