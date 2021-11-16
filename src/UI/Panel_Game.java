package UI;

import controllers.GameController;
import controllers.PlayerControl;
import game.GameDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel_Game extends JPanel {
    private Layer[] layers;
    private JButton btnStart;
    private JButton btnPause;
    private GameController gameController;
    private GameDataManager gameDataManager;


    public Panel_Game(GameController gameController, GameDataManager gameDataManager) {
        this.gameController = gameController;
        this.gameDataManager = gameDataManager;
        this.addKeyListener(new PlayerControl(gameController));
        this.setLayout(null);
        layers = new Layer[]{
                new Layer_BG(0, 0, 1200, 700),
                new Layer_Game(500, 35, 200, 600),
                new Layer_Score(50, 50, 300, 200),
                new Layer_Rank(50, 300, 300, 300),
                new Layer_Next(850, 50, 300, 300),
                new Layer_Button(850, 400, 300, 200)
        };
        initButton();

    }

    private void initButton() {
        ImageIcon startIm = new ImageIcon("Graphics/Strings/Start.png");
        ImageIcon pauseIm = new ImageIcon("Graphics/Strings/PauseB.png");
        this.btnStart = new JButton(startIm);
        btnStart.setBounds(890,420,210,75);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.start();
            }
        });
        this.add(btnStart);

        this.btnPause = new JButton(pauseIm);
        btnPause.setBounds(890,520,210,75);
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameDataManager.switchPause();
            }
        });
        this.add(btnPause);

    }


    @Override
    public void paintComponent(Graphics g) {
        // Call basic search
        super.paintComponent(g);

        // Refresh game interface
        for (int i = 0; i < layers.length; i++) {
            layers[i].setGameDataManager(this.gameDataManager);
            //draw the windows
            layers[i].paint(g);
        }
        this.requestFocus();
    }



    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void buttonSwitch(boolean on){
        this.btnStart.setEnabled(on);
    }
}
