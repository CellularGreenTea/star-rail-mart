package net.cgt.iface.components.container.payment;

import net.cgt.iface.components.container.menu.CheckoutView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class PaymentContainer extends JPanel {
    public PaymentContainer() {
        super();
        setLayout(new MigLayout(
                "insets 3% 3% 3% 3%, fill, flowx",
                "[grow, fill]1%[grow, fill]1%[grow, fill]"));
        setOpaque(false);

        add(new PaymentOptions(), "align 0% 0%, width 15%, height 40%");
        add(new PaymentContent(), "width 50%, height 100%");
        add(new CheckoutView(), "width 35%, height 100%");
    }
}
        