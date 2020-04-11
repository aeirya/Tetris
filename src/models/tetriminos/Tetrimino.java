package models.tetriminos;

import models.DrawList;
import models.Drawable;
import models.GameObject;
import models.IGameObject;
import models.IShape;
import java.awt.Graphics;
import ui.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, Drawable {
    
    IGameObject body;
    IShape shape;
    DrawList leonardoDaVinci;

    public Tetrimino(IShape shape, int x, int y, Color color) {
        this(shape, 
            Architect.getInstance().new Box(x,y,color)
        );
    }

    public Tetrimino(IShape shape, int x, int y) {
        this.shape = shape;
        this.body = new GameObject(Architect.getInstance().genBox(), x, y);
        update();
    }

    private Tetrimino(IShape shape, IGameObject body) {
        this.shape = shape;
        this.body = body;
        update();
    }

    public void rotate() {
        shape.rotate();
        update();
    }

    public void move(int dx, int dy) {
        body.move(dx, dy);
        update();
    }

    private void update() {
        leonardoDaVinci = shape.applyShape(body);
    }

    public void draw(Graphics g) {
        leonardoDaVinci.draw(g);
    }

    public IGameObject copy() {
        return new Tetrimino(shape, body);
    }
}