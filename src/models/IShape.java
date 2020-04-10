package models;

public interface IShape {
    void rotate();
    Coordinate getCoordinate(int i);
    DrawList applyShape(IGameObject body);
}