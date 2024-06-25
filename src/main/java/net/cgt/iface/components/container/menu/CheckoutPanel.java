package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.CleanJTable;
import net.cgt.iface.boilerplate.components.ModernScrollbar;
import net.cgt.iface.components.ApplicationWindow;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Vector;

public class CheckoutPanel extends JPanel {
    public static Vector<Vector<Object>> checkoutList = ApplicationWindow.checkoutData.getCheckoutItems();
    public static Vector<Object> tableHeaders = new Vector<>();
    public CleanJTable checkoutTable;
    public static JScrollPane checkout;

    class Checkout extends JScrollPane {
        public Checkout() {
            super();
            setBorder(new EmptyBorder(0, 0, 0, 0));
            setOpaque(false);
            getViewport().setOpaque(false);
            setViewportBorder(null);
            getVerticalScrollBar().setUnitIncrement(20);
            getHorizontalScrollBar().setUnitIncrement(20);

            setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
            setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JScrollBar modernVerticalScrollbar = getVerticalScrollBar();
            modernVerticalScrollbar.setUI(new ModernScrollbar());
            modernVerticalScrollbar.setOpaque(false);
            modernVerticalScrollbar.addMouseListener(new Mouse());

            JScrollBar modernHorizontalScrollbar = getHorizontalScrollBar();
            modernHorizontalScrollbar.setUI(new ModernScrollbar());
            modernHorizontalScrollbar.setOpaque(false);
            modernHorizontalScrollbar.addMouseListener(new Mouse());

            tableHeaders.add("No.");
            tableHeaders.add("Code");
            tableHeaders.add("Name");
            tableHeaders.add("Price");
            tableHeaders.add("Quantity");

            checkoutTable = new CleanJTable(checkoutList, tableHeaders);
            checkoutTable.setBorder(null);

            setViewportView(checkoutTable);
        }
    }

    public CheckoutPanel() {
        super(new MigLayout("align 50% 50%, fill"));
        setOpaque(false);

        checkout = new Checkout();
        add(checkout, "grow");
    }

    public void updateTable() {
        checkoutList.clear();
        checkoutList.addAll(ApplicationWindow.checkoutData.getCheckoutItems());

        MenuContainer.checkoutView.revalidate();
        MenuContainer.checkoutView.repaint();
    }
}
