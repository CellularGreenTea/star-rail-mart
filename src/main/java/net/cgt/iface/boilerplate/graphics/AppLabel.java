package net.cgt.iface.boilerplate.graphics;

import javax.swing.*;
import java.awt.*;

public class AppLabel extends JLabel {
    private static class CustomFontWrapper extends Font {
        public CustomFontWrapper(int size, String fontName) {
            super(fontName, Font.PLAIN, size);
        }
    }

    public AppLabel(String label, Color color, int size, FontType fontType) {
        super();

        String font = "";

        switch (fontType) {
            case FontType.REGULAR -> font = "Inter Regular";
            case FontType.MEDIUM -> font = "Inter Medium";
            case FontType.BOLD -> font = "Inter Bold";
        }

        setText(label);
        setForeground(color);
        setFont(new CustomFontWrapper(size, font));
    }
}

