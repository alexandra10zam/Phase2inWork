package UI;

import javax.swing.*;
import java.awt.*;

public class Layer_Next extends Layer {

    protected Image nextIm = new ImageIcon("Graphics/Strings/Next.png").getImage();

    public Layer_Next(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        g.drawImage(nextIm, this.x + 15, this.y + 15, this.x + 255, this.y + 105, 0, 0, 800, 300, null);

        if (this.gameDataManager.isStart()) {
            String docStr = "Graphics/Game/block" + gameDataManager.getNext() + ".png";
            Image blocki = new ImageIcon(docStr).getImage();

            // Print grids
            Point[] points = gameDataManager.getActPent().getPoints(gameDataManager.getNext());
            if (points == null)
                return;

            for (int i = 0; i < points.length; i++) {
                g.drawImage(blocki, this.x + 55 + points[i].x * 40, this.y + 120 + points[i].y * 40, this.x + 55 + points[i].x * 40 + 40, this.y + 120 + points[i].y * 40 + 40, 0, 0, 40, 40, null);
            }
        }
    }
}
