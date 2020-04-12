package models;

public interface IShape {
    void rotate(int i);
    default void rotateLeft() {
        rotate(1);
    }
    default void rotateRight() {
        rotate(-1);
    }
    void revert();
    DrawList applyShape(IGameObject body);
}