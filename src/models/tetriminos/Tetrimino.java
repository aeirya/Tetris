package models.tetriminos;

import models.DrawList;
import models.Drawable;
import models.GameObject;
import models.IGameObject;
import models.IShape;
import java.awt.Graphics;
import ui.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, IShape, Drawable {
    
    IGameObject body;
    IShape shape;
    DrawList leonardoDaVinci;
    boolean isLastActionMove = true;

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

    public void rotate(int i) {
        isLastActionMove = false;
        shape.rotate(i);
        update();
    }

    public void move(int dx, int dy) {
        isLastActionMove = true;
        body.move(dx, dy);
        update();
    }

    private void update() {
        leonardoDaVinci = applyShape(body);
    }

    public void draw(Graphics g) {
        leonardoDaVinci.draw(g);
    }

    public IGameObject copy() {
        return new Tetrimino(shape, body);
    }

    public void revert() {
        if (isLastActionMove) body.revert();
        else shape.revert();
        update();
    }
    
    public boolean collides(IGameObject[][] objects) {
        return leonardoDaVinci.collides(objects);
    }

    // private Box[] dissociate() {
    //     // 
    //     return null;
    // }

    public void addTo(IGameObject[][] map) {
        // leonardoDaVinci.   
    }

    public DrawList applyShape(IGameObject body) {
        return shape.applyShape(body);
    }
}