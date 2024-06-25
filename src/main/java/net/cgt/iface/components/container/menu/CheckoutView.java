package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.miginfocom.swing.MigLayout;

public class CheckoutView extends RoundJPanel {
    public static CheckoutPanel checkoutPanel;

    public CheckoutView() {
        super(Palette.SECONDARY.getColor(), 20, false);
        super.setLayout(new MigLayout("align 50% 0%, fill, flowy"));

        checkoutPanel = new CheckoutPanel();

        add(checkoutPanel, "grow");
    }
}
