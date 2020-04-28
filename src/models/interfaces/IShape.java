package models.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IShape extends Serializable {
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