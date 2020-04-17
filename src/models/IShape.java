package models;

import java.util.List;

public interface IShape {
    void rotate(int i);
    default void rotateLeft() {
        rotate(1);
    }
    default void rotateRight() {
        rotate(-1);
    }
    void revert();
    List<IGameObject> applyShape(IGameObject body);
}