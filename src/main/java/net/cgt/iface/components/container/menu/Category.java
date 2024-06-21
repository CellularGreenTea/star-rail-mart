package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.AppLabel;
import net.cgt.iface.boilerplate.graphics.FontType;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Category extends RoundJPanel {
    private final String label;

    public Category(String label) {
        super(Palette.CLEAR.getColor(), 30, false);

        this.label = label;

        setLayout(new MigLayout("align center"));
        AppLabel categoryLabel = new AppLabel(label, Palette.TEXT_GRAY.getColor(), 16, FontType.MEDIUM);

        add(categoryLabel);
        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuCategory.activeCategory != null && !MenuCategory.activeCategory.getLabel().equals(((Category) e.getComponent()).getLabel())) {
                    MenuCategory.activeCategory.setBackgroundColor(super.getComponentColor());
                    MenuCategory.activeCategory.setLabelColor(Palette.TEXT_GRAY.getColor());
                }

                MenuCategory.activeCategory = (Category) e.getComponent();
                MenuCategory.activeCategory.setBackgroundColor(Palette.SECONDARY.getColor());
                MenuCategory.activeCategory.setLabelColor(Palette.TEXT_WHITE.getColor());

                switch (MenuCategory.activeCategory.getLabel()) {
                    case "All" -> Menu.loadAllCategoryItems();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                Category c = (Category) e.getComponent();

                if (MenuCategory.activeCategory == null ||
                        !MenuCategory.activeCategory.getLabel().equals(c.getLabel())
                ) {
                    super.setComponentColor(c.getBackgroundColor());
                    c.setLabelColor(Palette.TEXT_WHITE.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                Category c = (Category) e.getComponent();

                if (MenuCategory.activeCategory == null ||
                        !MenuCategory.activeCategory.getLabel().equals(c.getLabel())
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
