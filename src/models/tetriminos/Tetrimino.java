package models.tetriminos;

import models.Animate;
import models.DrawList;
import models.Drawable;
import models.GameObject;
import models.IGameObject;
import models.IShape;
import java.awt.Graphics;

import controllers.ICommand;
import ui.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, IShape, Drawable, Animate {
    
    private IGameObject body;
    private IShape shape;
    private DrawList leonardoDaVinci;
    private boolean isLastActionMove = true;
    private ICommand animation;

    public Tetrimino(IShape shape, int x, int y, Color color) {
        this(shape, 
            Architect.getInstance().new Box(x,y,color)
        );
        util.log.GameLogger.log("Successfully created the tetrimino");
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

    public void freeFall() {
        fall();
        // Thread.sleep();
        fall();
        fall();
        fall();
        fall();
        fall();
        fall();
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

    public void addTo(IGameObject[][] map) {
        leonardoDaVinci.addTo(map);
    }

    public DrawList applyShape(IGameObject body) {
        return shape.applyShape(body);
    }

    public void setAnimation(ICommand cmd) {
        animation = cmd;
    }

    public void playAnimation() {
        if (animation!=null) animation.act(this);
    }
}