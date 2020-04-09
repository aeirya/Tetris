package models;

public interface IGameObject {
    
    void move(int x, int y);

    default void moveLeft() {
        move(-1,0);
    }
    
    default void moveRight() {
        move(1,0);
    }

    default void fall() {
        move(0, 1);
    }
}