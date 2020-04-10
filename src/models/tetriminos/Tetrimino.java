package models.tetriminos;

import models.Coordinate;
import models.Drawable;
import models.GameObject;
import models.IGameObject;
import models.IShape;
import ui.Architect.Box;;

import java.awt.Graphics;

public abstract class Tetrimino implements ITetrimono {
    
    IGameObject body;
    IShape shape;

    protected Tetrimino() {
        body.addComponents(objects);
    }

    private Box[] generatePixels() {
        
    }

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

    public void draw(Graphics g) {
        body.copy().move(c);
    }
}