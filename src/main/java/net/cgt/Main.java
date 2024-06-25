package net.cgt;

import net.cgt.iface.components.ApplicationWindow;
import net.cgt.service.checkout.CheckoutService;
import net.cgt.service.db.DBService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        DBService db = new DBService();
        CheckoutService checkDb = new CheckoutService();
        checkDb.removeAllCheckoutItems();

        SwingUtilities.invokeLater(() -> new ApplicationWindow("v2.0", db, checkDb));
    }
}