package net.cgt.iface.components;

import net.cgt.iface.boilerplate.graphics.ImageRender;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.cgt.iface.components.container.Container;
import net.cgt.iface.components.title.Title;
import net.cgt.service.checkout.CheckoutService;
import net.cgt.service.db.DBService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {
    public static final String title = "Star Rail Mart";
    public static String version;
    public static final Dimension overallSize = new Dimension(1450, 800);
    public static final Dimension titleBarSize = new Dimension(overallSize.width, 32);

    public static Title titleBar;
    public static Container container;

    public static DBService data;
    public static CheckoutService checkoutData;

    public ApplicationWindow(String progVersion, DBService db, CheckoutService checkDb) {
        super();
        version = progVersion;
        data = db;
        checkoutData = checkDb;

        setUndecorated(true);
        setPreferredSize(overallSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageRender("src/main/resources/icons/misc/logo.png", 500, 500).getImage());
        setTitle(title);
        setLayout(new MigLayout("insets 0"));
        getContentPane().setBackground(Palette.BACKGROUND.getColor());

        initComponents();

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        container = new Container();
        titleBar = new Title(this);

        add(titleBar, "dock north");
        add(container, "dock center");
        titleBar.fireMenuClick();
    }
}
