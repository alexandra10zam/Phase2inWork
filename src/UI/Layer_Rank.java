package UI;

import game.Record;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layer_Rank extends Layer {
    protected Image rankIm = new ImageIcon("Graphics/Strings/Rank.png").getImage();


    public Layer_Rank(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void paint(Graphics g) {
        this.createWindow(g);
        g.drawImage(rankIm, this.x + 15, this.y + 15, this.x + 255, this.y + 105, 0, 0, 800, 300, null);

        List<Record> records = this.gameDataManager.getRecords();
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        for (int i = 0; i < 5; i++) {
            g.drawString(i + 1 + ": " + records.get(i).getName(), this.x + 15, this.y + 130 + 35 * i);
            g.drawString("" + records.get(i).getScore(), this.x + 225, this.y + 130 + 35 * i);
        }

    }
}
