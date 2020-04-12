package models;

public interface IGameObject {
    
    void move(int x, int y);

    /** Returns a clone of game object which has moved to the coordinate c */
    default IGameObject updatedCoordinates(Coordinate c) {
        IGameObject go = copy();
        go.move(c.getX(), c.getY());
        return go;
    }

    default void moveLeft() {
        move(-1,0);
    }
    
    default void moveRight() {
        move(1,0);
    }

    default void fall() {
        move(0, 1);
    }

    void revertMove();
    // void collides(Coor)
    IGameObject copy();
}