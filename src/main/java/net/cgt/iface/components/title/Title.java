package net.cgt.iface.components.title;

import net.cgt.iface.boilerplate.adapter.Mouse;
import net.cgt.iface.boilerplate.components.RoundJPanel;
import net.cgt.iface.boilerplate.graphics.AppLabel;
import net.cgt.iface.boilerplate.graphics.FontType;
import net.cgt.iface.boilerplate.graphics.Palette;
import net.cgt.iface.components.ApplicationWindow;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class Title extends JPanel {
    private static final Rectangle deviceBounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                                  .getMaximumWindowBounds();

    private static final Point deviceBoundCenter = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                                  .getCenterPoint();

    private int mouseXPos, mouseYPos;

    public ApplicationWindow app;

    public Tab menuTab = null;
    public Tab checkoutTab = null;
    public Tab toolsTab = null;
    public static Tab activeTabSelection = null;

    public Title(ApplicationWindow window) {
        super(new MigLayout(
                "insets 0 0 0 0", "[sg][sg][sg]")
        );
        setPreferredSize(ApplicationWindow.titleBarSize);
        setBackground(Palette.BACKGROUND.getColor().darker());

        this.app = window;

        add(createTabSelectionContainer(), "dock west");
        add(createWindowHeaderContainer(), "dock center");
        add(createWindowActionButtonContainer(), "dock east");

        attachTitleBarListener();
    }

    private JPanel createTabSelectionContainer() {
        JPanel container = new JPanel();
        container.setLayout(new MigLayout(
                "insets 5 6 0 0", "[sg, fill]2lp[sg, fill]2lp[sg, fill]"
        ));
        container.setOpaque(false);

        menuTab = new Tab("Menu");
        checkoutTab = new Tab("Checkout");
        toolsTab = new Tab("Tools");

        container.add(menuTab);
        container.add(checkoutTab);
        container.add(toolsTab);

        return container;
    }

    private JPanel createWindowHeaderContainer() {
        JPanel windowHeaderContainer = new JPanel();
        windowHeaderContainer.setLayout(new MigLayout("align 55% 50%"));
        windowHeaderContainer.setOpaque(false);

        AppLabel headerLabel =  new AppLabel(ApplicationWindow.title + " " + ApplicationWindow.version, Palette.TEXT_WHITE.getColor(), 15, FontType.MEDIUM);

        windowHeaderContainer.add(headerLabel);

        return windowHeaderContainer;
    }

    private JPanel createWindowActionButtonContainer() {
        JPanel container = new JPanel();
        container.setLayout(new MigLayout(
                "insets 3 0 0 10", "[sg, fill]2lp[sg, fill]2lp[sg, fill]"
        ));
        container.setOpaque(false);

        RoundJPanel minimizeButton = generateWindowAction("//");
        RoundJPanel maximizeButton = generateWindowAction("~");
        RoundJPanel closeButton = generateWindowAction("F4");

        container.add(minimizeButton);
        container.add(maximizeButton);
        container.add(closeButton);

        return container;
    }

    private RoundJPanel generateWindowAction(String label) {
        RoundJPanel buttonComponent = new RoundJPanel(Palette.CLEAR.getColor(), 10, false);
        AppLabel buttonLabel = new AppLabel(label, Palette.TEXT_WHITE.getColor(), 14, FontType.MEDIUM);

        buttonComponent.add(buttonLabel);
        buttonComponent.addMouseListener(new Mouse() {
            public void mouseClicked(MouseEvent e) {
                switch (label) {
                    case "//":
                        app.setExtendedState(JFrame.ICONIFIED);
                        break;

                    case "~":
                        if (app.getSize().equals(deviceBounds.getSize())) {
                            app.setSize(app.getPreferredSize());
                        }
                        else {
                            app.setSize(deviceBounds.getSize());
                        }

                        app.setLocation(deviceBoundCenter.x - app.getWidth() / 2,
                                        deviceBoundCenter.y - app.getHeight() / 2
                                        );
                        break;

                    default:
                        app.dispose();
                        System.exit(0);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);

                buttonComponent.setBackgroundColor(Palette.BACKGROUND_CONTENT.getColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                buttonComponent.setBackgroundColor(Palette.CLEAR.getColor());
            }
        });

        return buttonComponent;
    }

    private void attachTitleBarListener() {
        addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent event) {
                super.mouseClicked(event);

                if (event.getClickCount() == 2) {
                    if (app.getSize().equals(deviceBounds.getSize())) {
                        app.setSize(app.getPreferredSize());
                    }
                    else {
                        app.setSize(deviceBounds.getSize());
                    }

                    app.setLocation(deviceBoundCenter.x - app.getWidth() / 2,
                                    deviceBoundCenter.y - app.getHeight() / 2
                                    );
                    // Positions the window to the center of the screen
                }
            }

            @Override
            public void mousePressed(MouseEvent event) {
                mouseXPos = event.getX();
                mouseYPos = event.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                if (app.getSize().equals(deviceBounds.getSize())) {
                    app.setSize(app.getPreferredSize());
                }

                app.setLocation(event.getXOnScreen() - mouseXPos, event.getYOnScreen() - mouseYPos);
            }
        });
    }

    public void fireMenuClick() {
        MouseEvent clickEvent = new MouseEvent(
                menuTab,
                MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(),
                0,
                menuTab.getHeight() / 2, menuTab.getWidth() / 2,
                1,
                false
        );
        menuTab.getMouseListeners()[0].mouseClicked(clickEvent);
    }
}
