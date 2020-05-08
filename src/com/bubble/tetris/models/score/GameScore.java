package com.bubble.tetris.models.score;

import java.io.Serializable;

public class GameScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private int linesRemoved = 0;
    private int level = 0;
    private int penalty = 0;

    public int getScore() {
        return level + 10 * linesRemoved - 5 * penalty;
    }

    public void nextLevel() {
        level += 1;
    }

    public GameScore applyRevivePentaly() {
        penalty += 1;
        return this;
    }

    public int getRemovedLines() {
        return linesRemoved;
    }

    public void removedLine(int n) {
        linesRemoved += n;
    }
}