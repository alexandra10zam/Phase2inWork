package UI;

import javax.swing.*;
import java.awt.*;

public class Layer_Score extends Layer {
    protected Image scoreIm = new ImageIcon("Graphics/Strings/Score.png").getImage();


    public Layer_Score(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        g.drawImage(scoreIm, this.x + 15, this.y + 15, this.x + 255, this.y + 105, 0, 0, 800, 300, null);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,64));
        // Draw String: Left-bottom coordinates
        g.drawString(this.gameDataManager.getCurrentScore() + "", this.x + 15, this.y + 180);
    }
}
