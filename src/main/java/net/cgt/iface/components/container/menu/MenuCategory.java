package net.cgt.iface.components.container.menu;

import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.miginfocom.swing.MigLayout;

import java.awt.event.MouseEvent;

public class MenuCategory extends RoundJPanel {
    public static Category activeCategory;
    public Category allCategory;
    public Category mealsCategory;
    public Category dessertsCategory;
    public Category beveragesCategory;
    public Category specialsCategory;

    public MenuCategory() {
        super(Palette.BACKGROUND_CONTENT.getColor(), 20, false);
        setLayout(new MigLayout("align 50% 20%, flowy, fillx"));

        allCategory = new Category("All");
        mealsCategory = new Category("Meals");
        dessertsCategory = new Category("Desserts");
        beveragesCategory = new Category("Beverages");
        specialsCategory = new Category("Specials");

        add(allCategory, "growx");
        add(mealsCategory, "growx");
        add(dessertsCategory, "growx");
        add(beveragesCategory, "growx");
        add(specialsCategory, "growx");

        fireAllMenuClick();
    }

    public void fireAllMenuClick() {
        MouseEvent clickEvent = new MouseEvent(
                allCategory,
                MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(),
                0,
                allCategory.getHeight() / 2, allCategory.getWidth() / 2,
                1,
                false
        );
        allCategory.getMouseListeners()[0].mouseClicked(clickEvent);
    }
}
