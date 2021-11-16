package UI;

import javax.swing.*;
import java.awt.*;

public class Layer_BG extends Layer {
    protected Image bg = new ImageIcon("Graphics/BackGround.png").getImage();

    protected Layer_BG(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void paint(Graphics g) {
        g.drawImage(bg, x, y, w, h, null);
    }
}
