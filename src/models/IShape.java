package models;

public interface IShape {
    void rotate();
    void revert();
    DrawList applyShape(IGameObject body);
}