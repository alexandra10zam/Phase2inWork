package UI;

import game.GameDataManager;

import javax.swing.*;
import java.awt.*;

/**
 * To paint the windows
 */
public abstract class Layer {
    private static final int frameWidth = 5;
    private static final Image window = new ImageIcon("Graphics/window.png").getImage();
    protected GameDataManager gameDataManager = null;

    protected int x; // The left-top position x value
    protected int y; // The left-top position y value
    protected int w; // Window's width (inside)
    protected int h; // Window's height (inside)

    protected Layer(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    /**
     * Draw the window.
     *
     * @param g
     */
    protected void createWindow(Graphics g) {
        g.drawImage(window, x, y, w, h, null);
    }

    public void setGameDataManager(GameDataManager gameDataManager) {
        this.gameDataManager = gameDataManager;
    }

    protected abstract void paint(Graphics g);


}
