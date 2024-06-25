package net.cgt.iface.boilerplate.components;

import net.cgt.iface.boilerplate.graphics.Palette;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollbar extends BasicScrollBarUI  {
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        //Essentially, doing nothing here removes the track of the scrollbar
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        int orientation = scrollbar.getOrientation();
        int x = thumbBounds.x + 2;
        int y = thumbBounds.y + 2;

        int width = orientation == JScrollBar.VERTICAL ?
                10 : thumbBounds.width - (2 * 2);

        width = Math.max(width, 10);

        int height = orientation == JScrollBar.VERTICAL ?
                thumbBounds.height - (2 * 2) : 10;

        height = Math.max(height, 4);

        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Palette.PRIMARY.getColor());
        graphics2D.fillRoundRect(x, y, width, height, 10, 10);
        graphics2D.dispose();
    }


}
