package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_Game extends JFrame {
    private JButton btnStart;
    private JButton btnBot;
    Panel_Game panelGame;

    public Frame_Game(Panel_Game panelGame, Panel_mainPage panelMainPage) {
        this.setLayout(null);
        //Set title
        this.setTitle("Tetris Game with Pentominos");
        //Set default shut down program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        this.setSize(1200, 700);
        // Not allow user to change size
        this.setResizable(false);
        this.panelGame = panelGame;

        // Make the window in the middle
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        this.setContentPane(panelGame);
        //Set default visible
        this.setVisible(true);
    }

    private void initButton(Panel_Game panelGame) {
        ImageIcon startIm = new ImageIcon("Graphics/Strings/Start.png");
        ImageIcon pauseIm = new ImageIcon("Graphics/Strings/PauseB.png");
        this.btnStart = new JButton(startIm);
        btnStart.setBounds(495,313,210,75);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel();
            }
        });
        this.add(btnStart);


        this.btnBot = new JButton(pauseIm);
        btnBot.setBounds(495,413,210,75);
        btnBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(btnBot);
    }

    public void changePanel(){
        this.setContentPane(panelGame);
    }


}
