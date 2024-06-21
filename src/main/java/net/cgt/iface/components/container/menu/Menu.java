package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.ModernScrollbar;
import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.AppLabel;
import net.cgt.iface.boilerplate.graphics.FontType;
import net.cgt.iface.boilerplate.graphics.ImageRender;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.cgt.iface.components.ApplicationWindow;
import net.cgt.service.item.Item;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class ItemCard extends RoundJPanel {
    private final String itemCode;
    private final String itemName;
    private final double itemPrice;
    private int itemStock;

    public ItemCard(Item item) {
        super(Palette.BACKGROUND_CONTENT.getColor(), 30, false);
        setLayout(new MigLayout("align left", "1%[]2%[]2%[]1%"));
        this.itemCode = item.getCode();
        this.itemName = item.getName();
        this.itemPrice = item.getPrice();
        this.itemStock = item.getStock();

        AppLabel code = new AppLabel(this.itemCode, Palette.TEXT_WHITE.getColor(), 18, FontType.MEDIUM);
        JLabel icon = new JLabel();
        String iconPath = "src/main/resources/icons/items/";
        icon.setIcon(new ImageRender(iconPath + this.itemCode.toLowerCase() + ".png", 70, 70));

        add(code);
        add(icon);
        add(generateItemDetails());

        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                RoundJPanel c = (RoundJPanel) e.getComponent();

                super.setComponentColor(c.getBackgroundColor());
                c.setBackgroundColor(Palette.BACKGROUND_HIGHLIGHT.getColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                RoundJPanel c = (RoundJPanel) e.getComponent();

                c.setBackgroundColor(super.getComponentColor());
            }
        });
    }

    private JPanel generateItemDetails() {
        JPanel subPanel = new JPanel(new MigLayout("wrap 1"));
        subPanel.setOpaque(false);

        AppLabel name = new AppLabel(this.itemName, Palette.TEXT_WHITE.getColor(), 15, FontType.REGULAR);
        AppLabel price = new AppLabel(String.valueOf(this.itemPrice), Palette.TEXT_WHITE.getColor(), 13, FontType.REGULAR);
        AppLabel stock = new AppLabel(String.valueOf(this.itemStock), Palette.TEXT_GRAY.getColor(), 13, FontType.REGULAR);

        subPanel.add(name);
        subPanel.add(price);
        subPanel.add(stock);
        
        return subPanel;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }
}

public class Menu extends JScrollPane {
    public static final JPanel viewport = new JPanel();

    public Menu() {
        super(viewport);
        setBorder(null);
        setOpaque(false);
        getViewport().setOpaque(false);
        setViewportBorder(null);

        JScrollBar modernScrollbar = getVerticalScrollBar();
        modernScrollbar.setUI(new ModernScrollbar());
        modernScrollbar.setOpaque(false);

        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        viewport.setLayout(new MigLayout("wrap 2, fill", "[sg, fill]2%[sg, fill]", "[]0.7%"));
        viewport.setOpaque(false);
    }

    public static void loadAllCategoryItems() {
        Menu.viewport.removeAll();
        Menu.viewport.revalidate();
        Menu.viewport.repaint();

        ArrayList<Item> allItems = ApplicationWindow.data.getAllItems();

        for (Item item: allItems) {
            Menu.viewport.add(new ItemCard(item), "grow");
        }

        Menu.viewport.revalidate();
        Menu.viewport.repaint();
    }
}
