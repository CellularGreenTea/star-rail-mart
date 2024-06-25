package net.cgt.iface.components.container.payment;

import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.miginfocom.swing.MigLayout;

public class PaymentOptions extends RoundJPanel {
    public static Payment activePaymentOption;
    public Payment cashOption;
    public Payment gcashOption;
    public Payment cardOption;
    public Payment internetBankingOption;

    public PaymentOptions() {
        super(Palette.BACKGROUND_CONTENT.getColor(), 20, false);
        setLayout(new MigLayout("align 50% 20%, fillx, flowy"));

        cashOption = new Payment("Cash");
        gcashOption = new Payment("GCash");
        cardOption = new Payment("Credit/Debit Card");
        internetBankingOption = new Payment("Internet Banking");

        add(cashOption, "growx");
        add(gcashOption, "growx");
        add(cardOption, "growx");
        add(internetBankingOption, "growx");
    }
}
