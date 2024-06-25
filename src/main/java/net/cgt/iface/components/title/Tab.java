package net.cgt.iface.components.title;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.BoundRoundJPanel;
import net.cgt.iface.boilerplate.graphics.AppLabel;
import net.cgt.iface.boilerplate.graphics.FontType;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.cgt.iface.components.ApplicationWindow;

import java.awt.event.MouseEvent;

public class Tab extends BoundRoundJPanel {
    private final String label;

    public Tab(String label) {
        super();

        this.label = label;

        setBackground(Palette.CLEAR.getColor());
        setRoundTopLeft(15);
        setRoundTopRight(15);

        AppLabel tabLabel = new AppLabel(label, Palette.TEXT_WHITE.getColor(), 15, FontType.REGULAR);

        add(tabLabel);
        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Title.activeTabSelection != null && Title.activeTabSelection != e.getComponent()) {
                    Title.activeTabSelection.setBackground(Palette.CLEAR.getColor());
                }

                Title.activeTabSelection = (Tab) e.getComponent();
                Title.activeTabSelection.setBackground(Palette.BACKGROUND_HIGHLIGHT.getColor());

                if (Title.activeTabSelection.getLabel().equals("Menu")) {
                    ApplicationWindow.container.navToMenu();
                } else if (Title.activeTabSelection.getLabel().equals("Payment")) {
                    ApplicationWindow.container.navToPayment();
                } else {
                    ApplicationWindow.container.navToTools();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                if (Title.activeTabSelection != e.getComponent()) {
                    Tab c = (Tab) e.getComponent();

                    c.setBackground(Palette.BACKGROUND_CONTENT.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                if (Title.activeTabSelection != e.getComponent()) {
                    Tab c = (Tab) e.getComponent();

                    c.setBackground(Palette.CLEAR.getColor());
                }
            }
        });
    }

    public String getLabel() {
        return this.label;
    }
}
