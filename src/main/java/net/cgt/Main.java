package net.cgt;

import net.cgt.iface.components.ApplicationWindow;
import net.cgt.service.checkout.CheckoutService;
import net.cgt.service.db.DBService;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        DBService db = new DBService();
        CheckoutService checkDb = new CheckoutService();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus not found in the installed L&F's");
        }

        new ApplicationWindow("v2.0", db, checkDb);
    }
}
