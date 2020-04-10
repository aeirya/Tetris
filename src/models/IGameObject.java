package models;

public interface IGameObject {
    
    void move(int x, int y);

    default void move(Coordinate c) {
        move(c.getX(), c.getY());
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

    IGameObject[] getChildren();

    void addComponents(IGameObject... objects);

    void sumCoordinate(Coordinate c);

    IGameObject copy();
}