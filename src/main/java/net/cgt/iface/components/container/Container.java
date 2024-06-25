package net.cgt.iface.components.container;

import net.cgt.iface.components.container.menu.MenuContainer;
import net.cgt.iface.components.container.payment.PaymentContainer;
import net.cgt.iface.components.container.tools.ToolsContainer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class Container extends JPanel {
    MenuContainer menu;
    PaymentContainer checkout;
    ToolsContainer tools;

    public Container() {
        super(new MigLayout());
        super.setOpaque(false);

        menu = new MenuContainer();
        checkout = new PaymentContainer();
        tools = new ToolsContainer();
    }

    public void navToMenu() {
        removeAll();
        repaint();

        add(menu, "dock center");

        revalidate();
        repaint();
    }

    public void navToPayment() {
        removeAll();
        repaint();

        add(checkout, "dock center");

        revalidate();
        repaint();
    }

    public void navToTools() {
        removeAll();
        repaint();

        add(tools, "dock center");

        revalidate();
        repaint();
    }
}
