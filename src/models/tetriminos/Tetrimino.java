package models.tetriminos;

import models.DrawList;
import models.Drawable;
import models.GameObject;
import models.IGameObject;
import models.IShape;
import java.awt.Graphics;

import app.Game;
import controllers.Animator;
import controllers.ICommand;
import ui.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, IShape, Drawable {

    private final IGameObject body;
    private final IShape shape;
    private DrawList leonardoDaVinci;
    private boolean isLastActionMove = true;
    private Animator animator = new Animator();
    private boolean locked = false;

    public Tetrimino(final IShape shape, final int x, final int y, final Color color) {
        this(shape, Architect.getInstance().new Box(x, y, color));
        util.log.GameLogger.log("Successfully created the tetrimino");
    }

    public Tetrimino(final IShape shape, final int x, final int y) {
        this.shape = shape;
        this.body = new GameObject(Architect.getInstance().genBox(), x, y);
        update();
    }

    private Tetrimino(final IShape shape, final IGameObject body) {
        this.shape = shape;
        this.body = body;
        update();
    }

    public void rotate(final int i) {
        if (!locked) {
            isLastActionMove = false;
            shape.rotate(i);
            update();
        }
    }

    public void move(final int dx, final int dy) {
        if (!locked) {
            forceMove(dx, dy);
        }
    }
    
    private void forceMove(final int dx, final int dy) {
        isLastActionMove = true;
        body.move(dx, dy);
        update();
    }

    @Override
    public void fall() {
        forceMove(0, 1);
    }

    private void update() {
        leonardoDaVinci = applyShape(body);
    }

    public void draw(final Graphics g) {
        leonardoDaVinci.draw(g);
    }

    public IGameObject copy() {
        return new Tetrimino(shape, body);
    }

    public void revert() {
        if (isLastActionMove)
            body.revert();
        else
            shape.revert();
        update();
    }

    public boolean collides(final IGameObject[][] objects) {
        return leonardoDaVinci.collides(objects);
    }

    public void addTo(final IGameObject[][] map) {
        leonardoDaVinci.addTo(map); 
    }

    public DrawList applyShape(final IGameObject body) {
        return shape.applyShape(body);
    }

    public void setAnimation(ICommand animation) {
        animator.setAnimation(animation);
        animator.playAnimation(this);
    }

    public void stopAnimation() {
        animator.stopAnimation();
    }

    public void dash() {
        this.locked = true;
        Game.getInstance().changeGameSpeed();
    }
}