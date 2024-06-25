package net.cgt.iface.boilerplate.graphics;

import java.awt.Color;

public enum Palette {
    BACKGROUND(Color.decode("#0C0A0F")),
    BACKGROUND_CONTENT(Color.decode("#141217")),
    BACKGROUND_HIGHLIGHT(Color.decode("#1B191E")),
    TEXT_WHITE(Color.decode("#eae9ec")),
    TEXT_BLACK(Color.decode("#141316")),
    TEXT_GRAY(Color.decode("#86858A")),
    PRIMARY(Color.decode("#ad99cc")),
    SECONDARY(Color.decode("#482d76")),
    SECONDARY_HIGHLIGHT(Color.decode("#1E172A")),
    ACCENT(Color.decode("#804ed0")),
    GREEN(Color.decode("#3B7A57")),
    GREEN_HIGHLIGHT(Color.decode("#29AB87")),
    RED(Color.decode("#BF0A30")),
    CLEAR(new Color(0, 0, 0, 0));

    private final Color color;

    Palette(Color decode) {
        this.color = decode;
    }

    public Color getColor() {
        return this.color;
    }
}
