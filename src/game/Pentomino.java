package game;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Pentomino {
    private Point[] actPoints = null; // act meas the pentomino which is now on the board
    private int pentIndex;

    private static List<Point[]> PENT_LIST = PentominoDB.PENT_LIST;

    public Pentomino() {
        this.setActPoints(0);
    }

    public void setActPoints(int pentIndex) {
        if (pentIndex > 11) {
            Collections.shuffle(PENT_LIST);
            pentIndex = 0;
        }

        this.pentIndex = pentIndex;

        Point[] points = PENT_LIST.get(pentIndex);
        actPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            actPoints[i] = new Point(points[i].x, points[i].y);
        }

    }

    public Point[] getActPoints() {
        return actPoints;
    }

    public Point[] getPoints(int index) {
        if (index > 11)
            return null;

        return PENT_LIST.get(index);
    }


    public boolean move(int moveX, int moveY, boolean[][] gameMap) {
        for (int i = 0; i < actPoints.length; i++) {
            if (isOverZone(actPoints[i].x + moveX, actPoints[i].y + moveY, gameMap))
                return false;
        }

        for (int i = 0; i < actPoints.length; i++) {
            actPoints[i].x += moveX;
            actPoints[i].y += moveY;
        }
        return true;
    }

    /**
     * rotate 90 degree
     * <p>
     * A: final  O: center  B:original
     * A.x = O.y + O.x - B.y
     * A.y = O.y - O.x + B.x
     */
    public void rotate(boolean[][] gameMap) {
        rotateShift(gameMap);
        for (int i = 1; i < actPoints.length; i++) {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            actPoints[i].x = newX;
            actPoints[i].y = newY;
        }
    }

    private boolean isOverZone(int x, int y, boolean[][] gameMap) {
        return (x < 0 || x > 4 || y < 0 || y > 14 || gameMap[y][x]);
    }

    private void rotateShift(boolean[][] gameMap) {
        int horizontalShift = 0;
        int verticalShift = 0;
        for (int i = 0; i < actPoints.length; i++) {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;

            if (newX < 0) {
                horizontalShift = Math.max(horizontalShift, 0 - newX);
            } else if (newY < 0) {
                verticalShift = Math.max(verticalShift, 0 - newY);
            } else if (newX > 4) {
                horizontalShift = Math.min(horizontalShift, 4 - newX);
            } else if (newY > 14 || gameMap[newY][newX]) {
                return;
            }
        }
        this.move(horizontalShift, verticalShift, gameMap);
    }

    public int getPentIndex() {
        return pentIndex;
    }


}
