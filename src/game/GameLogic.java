package game;

import UI.Frame_SaveScore;

import java.awt.*;
import java.util.List;

/**
 * The pure logic part of game.
 * The gravity is Naive Gravity.
 * Implement the same thing with '7bags' in Tetris: There must be the same pentomino in 12 consecutive
 * The next pentomino is random
 * After a line is filled and removed, the score will increase 1.
 */
public class GameLogic {
    private GameDataManager gameDataManager;
    private int pentIndex;
    private Frame_SaveScore frame_saveScore;


    public GameLogic(GameDataManager gameDataManager) {
        this.gameDataManager = gameDataManager;
        gameDataManager.setActPent(new Pentomino());
        pentIndex = 1;
    }

    public void keyRight() {
        if (!gameDataManager.isStart() || gameDataManager.isPause())
            return;
        synchronized (this.gameDataManager) { // Thread security
            this.gameDataManager.getActPent().move(1, 0, this.gameDataManager.getGameMap());
        }
    }

    public void keyLeft() {
        if (!gameDataManager.isStart() || gameDataManager.isPause())
            return;
        synchronized (this.gameDataManager) {
            this.gameDataManager.getActPent().move(-1, 0, this.gameDataManager.getGameMap());
        }
    }

    public void keyUp() {
        if (!gameDataManager.isStart() || gameDataManager.isPause())
            return;

        synchronized (this.gameDataManager) {
            this.gameDataManager.getActPent().rotate(this.gameDataManager.getGameMap());
        }
    }

    public boolean keyDown() {
        if (!gameDataManager.isStart() || gameDataManager.isPause())
            return false;
        if (this.gameDataManager.getActPent().move(0, 1, this.gameDataManager.getGameMap()))
            return false;

        synchronized (this.gameDataManager) {
            boolean[][] map = this.gameDataManager.getGameMap();
            Point[] act = this.gameDataManager.getActPent().getActPoints();
            for (int i = 0; i < act.length; i++) {
                map[act[i].y][act[i].x] = true;
            }
            // Down move finish. Remove the lines, refresh, calculate score
            int removedLineNo = this.removedLineNo();
            gameDataManager.setCurrentScore(gameDataManager.getCurrentScore() + removedLineNo);

            this.gameDataManager.getActPent().setActPoints(this.gameDataManager.getNext());
            if (this.pentIndex >= 11) {
                this.pentIndex = 1;
            }
            this.gameDataManager.setNext(++pentIndex);

            if (this.isGameover()) {
                this.afterGameover();
            }
        }
        return true;
    }

    public void keySpace() {
        while (!this.keyDown()) ;
    }

    private void afterGameover() {
        this.gameDataManager.setStart(false);
        frame_saveScore.showSave(gameDataManager.getCurrentScore());
    }

    private boolean isGameover() { // Noel
        Point[] act = this.gameDataManager.getActPent().getActPoints();

        boolean[][] map = this.gameDataManager.getGameMap();
        int UPPER_BORDER = 0;
        for (int i = 0; i < act.length; i++) {
            if (map[act[i].y][act[i].x])
                return true;
            if (act[i].y < UPPER_BORDER)
                return true;
        }
        return false;
    }


    public void setRecordDB(List<Record> records) {
        this.gameDataManager.setRecords(records);
    }

//    public int removedLineNo() {
//        boolean[][] map = this.gameDataManager.getGameMap();
//        int counter = 0;
//
//        for (int y = 14; y > 0; y--) {
//            if (isFilled(y, map)) {
//                removeLines(y, map);
//                counter++;
//                y = 15;
//            }
//
//        }
//        return counter;
//    }
//
//    private void removeLines(int row, boolean[][] map) {
//        for (int x = 0; x < 5; x++) {
//            for (int y = row; y > 0; y--) {
//                map[y][x] = map[y - 1][x];
//            }
//            map[0][x] = false;
//        }
//    }
//
//    private boolean isFilled(int y, boolean[][] map) {
//        for (int x = 0; x < 5; x++) {
//            if (!map[y][x])
//                return false;
//        }
//        return true;
//    }

    public int removedLineNo() { //    Ada
        boolean[][] gameMap = this.gameDataManager.getGameMap();
        int counter = 0;
        boolean full;
        int j, i;

        for (i = 0; i < gameMap.length; i++) {
            full = true;
            inside:
            for (j = 0; j < gameMap[0].length; j++) {
                if (!gameMap[i][j]) {
                    full = false;
                    break inside;
                }
            }
            if (full) {
                counter++;
                removeLines(i, gameMap);
            }
        }
        return counter;
    }

    private void removeLines(int x, boolean[][] gameMap) { //    Ada
        int r = gameMap.length - 1;
        int c = gameMap[0].length - 1;

        for (int k = x; k > 0; k--) {
            gameMap[k] = gameMap[k - 1];
        }
        for (int a = 0; a <= c; a++) {
            gameMap[0][a] = false;
        }
    }

    public void startGame() {
        gameDataManager.setStart(true);
    }

    public void setFrame_saveScore(Frame_SaveScore frame_saveScore) {
        this.frame_saveScore = frame_saveScore;
    }
}
