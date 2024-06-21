package net.cgt.iface.components.container.menu;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MenuContainer extends JPanel {
    public MenuContainer() {
        super();
        setLayout(new MigLayout(
                "insets 3% 4% 3% 4%, fill, flowx",
                "[grow, fill]1%[grow, fill]1%[grow, fill]"));
        setOpaque(false);

        add(new MenuCategory(), "align 0% 0%, width 15%, height 40%");
        add(new Menu(), "width 55%, height 100%");
        add(new CheckoutView(), "width 30%, height 100% ");
    }
}
