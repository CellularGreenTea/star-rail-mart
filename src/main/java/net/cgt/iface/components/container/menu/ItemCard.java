package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.adapter.Mouse;
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
import java.util.Vector;

public class ItemCard extends RoundJPanel {
    private final AppLabel code;
    private final AppLabel name;
    private final AppLabel price;
    private final AppLabel stock;

    public ItemCard(Item item) {
        super(item.getStock() > 0 ? Palette.BACKGROUND_CONTENT.getColor() : Palette.CLEAR.getColor(),   30, false);
        setLayout(new MigLayout("align 0% 50%", "1%[]2%[]2%[]1%"));

        this.code = new AppLabel(item.getCode(), Palette.TEXT_GRAY.getColor(), 18, FontType.MEDIUM);
        JLabel icon = new JLabel(new ImageRender("src/main/resources/icons/items/" + item.getCode().toLowerCase() + ".png", 70, 70));

        this.name = new AppLabel(item.getName(), Palette.TEXT_GRAY.getColor(), 15, FontType.REGULAR);
        this.price = new AppLabel("Php. " + item.getPrice(), Palette.TEXT_GRAY.getColor(), 13, FontType.REGULAR);
        this.stock = new AppLabel(
                item.getStock() > 0 ? String.valueOf(item.getStock()) : "Out of Stock",
                item.getStock() > 0 ? Palette.GREEN.getColor() : Palette.RED.getColor(),
                13, FontType.REGULAR);

        add(this.code);
        add(icon);
        add(generateItemDetails());

        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int currentStock = item.getStock();

                if (currentStock >= 0) {
                    item.setStock(currentStock - 1);

                    ApplicationWindow.data.updateItemStock(item, item.getStock());
                    setItemStock(item.getStock() > 0 ? item.getStock() : "Out of Stock");

                    for (Vector<Object> v: ApplicationWindow.checkoutData.getCheckoutItems()) {
                        if (v.get(1).equals(item.getCode())) {
                            ApplicationWindow.checkoutData.updateCheckoutItemQuantity(item);
                            CheckoutView.checkoutPanel.updateTable();
                            return;
                        }
                    }

                    ApplicationWindow.checkoutData.addCheckoutItem(item);
                    CheckoutView.checkoutPanel.updateTable();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (item.getStock() > 0) {
                    ItemCard c = (ItemCard) e.getComponent();

                    super.setComponentColor(c.getBackgroundColor());

                    c.setBackgroundColor(Palette.BACKGROUND_HIGHLIGHT.getColor());

                    c.code.setForeground(Palette.TEXT_WHITE.getColor());
                    c.name.setForeground(Palette.TEXT_WHITE.getColor());
                    c.price.setForeground(Palette.TEXT_WHITE.getColor());
                    c.stock.setForeground(Palette.GREEN_HIGHLIGHT.getColor());

                    c.revalidate();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (item.getStock() > 0) {
                    ItemCard c = (ItemCard) e.getComponent();

                    c.setBackgroundColor(super.getComponentColor());

                    c.code.setForeground(Palette.TEXT_GRAY.getColor());
                    c.name.setForeground(Palette.TEXT_GRAY.getColor());
                    c.price.setForeground(Palette.TEXT_GRAY.getColor());
                    c.stock.setForeground(Palette.GREEN.getColor());

                    c.revalidate();
                }
            }
        });
    }

    private JPanel generateItemDetails() {
        JPanel subPanel = new JPanel(new MigLayout("wrap 1"));
        subPanel.setOpaque(false);

        subPanel.add(this.name);
        subPanel.add(this.price);
        subPanel.add(this.stock);

        return subPanel;
    }

    public void setItemStock(Object itemStock) {
        this.stock.setText(String.valueOf(itemStock));

        if (this.stock.getText().equals("Out of Stock")) {
            this.stock.setForeground(Palette.RED.getColor());
            setBackgroundColor(Palette.CLEAR.getColor());
        }

        revalidate();
        repaint();
    }
}