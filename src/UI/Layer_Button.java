package UI;

import javax.swing.*;
import java.awt.*;

public class Layer_Button extends Layer {
    protected Image pauseIm = new ImageIcon("Graphics/Strings/PauseB.png").getImage();
    protected Image startIm = new ImageIcon("Graphics/Strings/Start.png").getImage();


    public Layer_Button(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
    }
}
