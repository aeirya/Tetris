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

    class Coordinate {
        
        private int x = 0;
        private int y = 0;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}