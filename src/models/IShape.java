package models;

public interface IShape {
    void rotate();
    DrawList applyShape(IGameObject body);
}