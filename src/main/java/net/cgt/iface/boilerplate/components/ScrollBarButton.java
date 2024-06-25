package net.cgt.iface.boilerplate.components;

import javax.swing.*;

public class ScrollBarButton extends JButton {
    // Hides the decrease and increase button for the scrollbar

    public ScrollBarButton() {
        setVisible(false);
        setEnabled(false);
    }
}
