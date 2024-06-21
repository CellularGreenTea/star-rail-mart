package net.cgt.iface.boilerplate.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageRender extends ImageIcon {
    public ImageRender(String source, int width, int height) {
        super();

        Image renderedImage;

        try {
            renderedImage = ImageIO.read(new File(source)).getScaledInstance(width, height, Image.SCALE_SMOOTH);
            super.setImage(renderedImage);
        }
        catch (IOException ioErr) {
            System.err.println(ioErr.getMessage());
        }
    }
}
