package models;

public interface IShape {
    void rotate();
    Coordinate getCoordinate(int i);
    Drawable[] applyShape(IGameObject body);
}