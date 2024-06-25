package net.cgt.iface.components.container.payment;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.AppLabel;
import net.cgt.iface.boilerplate.graphics.FontType;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.cgt.iface.components.container.menu.Menu;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Payment extends RoundJPanel {
    private final String label;

    public Payment(String label) {
        super(Palette.CLEAR.getColor(), 30, false);

        this.label = label;

        setLayout(new MigLayout("align center"));
        AppLabel categoryLabel = new AppLabel(label, Palette.TEXT_GRAY.getColor(), 16, FontType.MEDIUM);

        add(categoryLabel);
        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (PaymentOptions.activePaymentOption != null && !PaymentOptions.activePaymentOption.getLabel().equals(((Payment) e.getComponent()).getLabel())) {
                    PaymentOptions.activePaymentOption.setBackgroundColor(super.getComponentColor());
                    PaymentOptions.activePaymentOption.setLabelColor(Palette.TEXT_GRAY.getColor());
                }

                PaymentOptions.activePaymentOption = (Payment) e.getComponent();
                PaymentOptions.activePaymentOption.setBackgroundColor(Palette.SECONDARY.getColor());
                PaymentOptions.activePaymentOption.setLabelColor(Palette.TEXT_WHITE.getColor());

                Menu.loadCategoryItems(PaymentOptions.activePaymentOption.getLabel());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                Payment c = (Payment) e.getComponent();

                if (PaymentOptions.activePaymentOption == null ||
                        !PaymentOptions.activePaymentOption.getLabel().equals(c.getLabel())
                ) {
                    super.setComponentColor(c.getBackgroundColor());
                    c.setLabelColor(Palette.TEXT_WHITE.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                Payment c = (Payment) e.getComponent();

                if (PaymentOptions.activePaymentOption == null ||
                        !PaymentOptions.activePaymentOption.getLabel().equals(c.getLabel())
                ) {
                    c.setLabelColor(Palette.TEXT_GRAY.getColor());
                }
            }
        });
    }

    public String getLabel() {
        return label;
    }

    public void setLabelColor(Color color) {
        AppLabel label = (AppLabel) getComponents()[0];
        label.setForeground(color);

        label.repaint();
    }
}
