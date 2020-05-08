package com.bubble.tetris.models;

public class Coordinate implements java.io.Serializable {
       
    private static final long serialVersionUID = 1L;
    
    protected int x = 0;
    protected int y = 0;

    public Coordinate() {
        this(0,0);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate sum(int x, int y) {
        return new Coordinate(this.x+x, this.y+y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}