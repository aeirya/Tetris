package controllers;

import java.io.Serializable;

public class GameScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private int linesRemoved = 0;
    private int level = 0;

    public int getScore() {
        return level + 10 * linesRemoved;
    }

    public void nextLevel() {
        level += 1;
    }

    public int getRemovedLines() {
        return linesRemoved;
    }

    public void removedLine(int n) {
        linesRemoved += n;
    }
}