package net.cgt.iface.components.container.menu;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MenuContainer extends JPanel {
    public static CheckoutView checkoutView;

    public MenuContainer() {
        super();
        setLayout(new MigLayout(
                "insets 3% 3% 3% 3%, fill",
                "[grow, fill]1%[grow, fill]1%[grow, fill]"));
        setOpaque(false);

        add(new MenuCategory(), "align 0% 0%, width 15%, height 40%");
        add(new Menu(), "align 0% 0%, width 55%, height 85%");

        checkoutView = new CheckoutView();
        add(checkoutView, "width 30%, height 100% ");
    }
}
