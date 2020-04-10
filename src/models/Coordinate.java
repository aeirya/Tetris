package models;

public class Coordinate {
        
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