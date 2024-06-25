package net.cgt.iface.boilerplate.graphics;

import javax.swing.*;
import java.awt.*;

public class AppLabel extends JLabel {
    private String label;

    private static class CustomFontWrapper extends Font {
        public CustomFontWrapper(int size, String fontName) {
            super(fontName, Font.PLAIN, size);
        }
    }

    public AppLabel(String label, Color color, int size, FontType fontType) {
        super();

        this.label = label;

        String font = "";

        switch (fontType) {
            case FontType.REGULAR -> font = "Inter Regular";
            case FontType.MEDIUM -> font = "Inter Medium";
            case FontType.BOLD -> font = "Inter Bold";
        }

        setText(this.label);
        setForeground(color);
        setFont(new CustomFontWrapper(size, font));
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

