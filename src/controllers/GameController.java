package controllers;

import UI.Frame_Game;
import UI.Frame_SaveScore;
import UI.Panel_Game;
import UI.Panel_mainPage;
import game.GameDataManager;
import game.GameLogic;
import game.RecordDB;


/**
 * Receive action of player's keyboard
 * Control the interface
 * Control the logic of game
 */
public class GameController {
    private Frame_SaveScore fSaveScore;
    private Panel_Game panelGame;
    private Panel_mainPage panelMainPage;
    private GameLogic gameLogic;
    private GameDataManager gameDataManager;

    private RecordDB data;

    private Thread gameThread = null;

    public GameController() {
        // Create game data manager
        this.gameDataManager = new GameDataManager();
        // Create game logic algorithm unit
        this.gameLogic = new GameLogic(gameDataManager);
        // Create game panel
        this.panelGame = new Panel_Game(this, gameDataManager);
        this.panelMainPage = new Panel_mainPage();

        this.data = new RecordDB();
        this.gameDataManager.setRecords(data.loadData());

        this.fSaveScore = new Frame_SaveScore(this);
        gameLogic.setFrame_saveScore(fSaveScore);

        new Frame_Game(panelGame,panelMainPage);

    }

    /**
     * Receive action from player controller. Call the method in gameLogic to do logic calculate.
     * After calculation, let panel to repaint the UI.
     */
    public void keyRight() {
        this.gameLogic.keyRight();
        this.panelGame.repaint();
    }

    public void keyLeft() {
        this.gameLogic.keyLeft();
        this.panelGame.repaint();
    }

    public void keyUp() {
        this.gameLogic.keyUp();
        this.panelGame.repaint();
    }

    public void keyDown() {
        this.gameLogic.keyDown();
        this.panelGame.repaint();
    }

    public void keySpace() {
        this.gameLogic.keySpace();
        this.panelGame.repaint();
    }


    /**
     * If the game is started, the thread will control the pentomino fall down
     */
    public void start() {
        this.panelGame.buttonSwitch(false);
        this.gameLogic.startGame();

        // Create a thread
        this.gameThread = new Thread() {
            @Override
            public void run() {
                while (gameDataManager.isStart()) {
                    try {
                        panelGame.repaint();
                        Thread.sleep(700);
                        if (gameDataManager.isPause()) {
                            continue;
                        }
                        gameLogic.keyDown();
                        panelGame.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.gameThread.start();
        this.panelGame.repaint();
    }

    public void saveScore(String name) {
        gameDataManager.addRecord(name);
        data.saveData(gameDataManager.getRecords());
    }

}
