package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.ModernScrollbar;
import net.cgt.iface.components.ApplicationWindow;
import net.cgt.service.item.Item;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class Menu extends JScrollPane {
    public static final JPanel viewport = new JPanel();

    public Menu() {
        super(viewport);
        setBorder(null);
        setOpaque(false);
        getViewport().setOpaque(false);
        setViewportBorder(null);
        getVerticalScrollBar().setUnitIncrement(20);

        JScrollBar modernScrollbar = getVerticalScrollBar();
        modernScrollbar.setUI(new ModernScrollbar());
        modernScrollbar.setOpaque(false);
        modernScrollbar.addMouseListener(new Mouse());

        viewport.setLayout(new MigLayout("wrap 2, fill", "[sg, fill]15lp[sg, fill]", "[fill]15lp"));
        viewport.setOpaque(false);
    }

    public static void loadCategoryItems(String category) {
        Menu.viewport.removeAll();
        Menu.viewport.repaint();

        ArrayList<Item> items;

        if (category.equals("All")) {
            items = ApplicationWindow.data.getAllItems();
        }
        else {
            items = ApplicationWindow.data.getItemsByCode(category);
        }

        for (Item item: items) {
            Menu.viewport.add(new ItemCard(item), "growx");
        }

        Menu.viewport.revalidate();
        Menu.viewport.repaint();
    }
}
