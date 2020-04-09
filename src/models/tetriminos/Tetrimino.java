package models.tetriminos;

import models.Drawable;
import models.IGameObject;
import models.IShape;
import ui.Architect.Box;

public abstract class Tetrimino implements Drawable {
    
    IGameObject body;
    IShape shape;

    public void rotate() {
        shape.rotate();
    }

    public void moveLeft() {
        body.moveLeft();
    }

    public void moveRight() {
        body.moveRight();
    }

    public void fall() {
        body.fall();
    }
}