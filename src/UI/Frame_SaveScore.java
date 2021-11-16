package UI;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame_SaveScore extends JFrame {
    private JButton btnOK;
    private JLabel jlScore, errMsg;
    private JTextField textField;
    private GameController gameController;

    public Frame_SaveScore(GameController gameController) {
        this.gameController = gameController;
        this.setTitle("Save?");
        this.setSize(250, 150);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.createCom();
        this.createAction();
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setVisible(false);
    }

    private void createAction() {
        this.btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                if (name.length() > 15) {
                    errMsg.setText("Your name is too long:c");
                } else if (name == null || "".equals(name)) {
                    errMsg.setText("Please enter your name:3");
                } else {
                    gameController.saveScore(name);
                    setVisible(false);
                    try {
                        Thread.sleep(700);
                        System.exit(0);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        });
    }

    private void createCom() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.jlScore = new JLabel();
        north.add(jlScore);
        this.add(north, BorderLayout.NORTH);
        // Create space of error message
        this.errMsg = new JLabel();
        this.errMsg.setForeground(Color.RED);
        north.add(errMsg);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.textField = new JTextField(10);
        center.add(new JLabel("Please enter your name: "));
        center.add(textField);
        this.add(center, BorderLayout.CENTER);


        // Create OK button
        this.btnOK = new JButton("OK!");
        // Create the south panel
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Add button in south panel
        south.add(btnOK);
        this.add(south, BorderLayout.SOUTH);
    }

    public void showSave(int score){
        this.jlScore.setText("Your score: " + score);
        this.setVisible(true);
    }

}
