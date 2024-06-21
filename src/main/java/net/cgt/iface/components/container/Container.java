package net.cgt.iface.components.container;

import net.cgt.iface.components.container.checkout.CheckoutContainer;
import net.cgt.iface.components.container.menu.MenuContainer;
import net.cgt.iface.components.container.tools.ToolsContainer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Container extends JPanel {
    MenuContainer menu = new MenuContainer();
    CheckoutContainer checkout = new CheckoutContainer();
    ToolsContainer tools = new ToolsContainer();

    public Container() {
        super(new MigLayout());
        super.setOpaque(false);
    }

    public void navToMenu() {
        removeAll();
        repaint();

        add(menu, "dock center");
        repaint();
    }

    public void navToCheckout() {
        removeAll();
        repaint();

        add(checkout, "dock center");
        repaint();
    }

    public void navToTools() {
        removeAll();
        repaint();

        add(tools, "dock center");
        repaint();
    }
}
