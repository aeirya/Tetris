package models.tetrimino;

import ui.drawlist.CollidableDrawList;
import util.audio.SoundEffect;
import models.GameObject;
import models.interfaces.Drawable;
import models.interfaces.IGameObject;
import models.interfaces.IShape;
import java.awt.Graphics;
import java.util.List;

import app.Game;
import controllers.Animator;
import controllers.input.ICommand;
import controllers.level.Map;
import models.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, IShape, Drawable {

    private final IGameObject body;
    private final IShape shape;
    private CollidableDrawList leonardoDaVinci;
    private boolean isLastActionMove = true;
    private final Animator animator = new Animator();

    public Tetrimino(final IShape shape, final int x, final int y, final Color color) {
        this(shape, Architect.getInstance().new Box(x, y, color));
        util.log.GameLogger.outdatedLog("Successfully created the tetrimino");
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
        isLastActionMove = false;
        shape.rotate(i);
        update();
    }

    public void move(final int dx, final int dy) {
        isLastActionMove = true;
        body.move(dx, dy);
        update();
    }

    private void update() {
        leonardoDaVinci = new CollidableDrawList(applyShape(body));
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
        isLastActionMove = !isLastActionMove;
        update();
    }

    public boolean collides(final Map objects) {
        return leonardoDaVinci.collides(objects);
    }

    public void addTo(final Map map) {
        leonardoDaVinci.addTo(map);
    }

    public List<IGameObject> applyShape(final IGameObject body) {
        return shape.applyShape(body);
    }

    public void setAnimation(final ICommand animation) {
        animator.setAnimation(animation);
        animator.playAnimation(this);
    }

    public void stopAnimation() {
        animator.stopAnimation();
    }

    public void dash() {
        Game.getInstance().changeGameSpeed();
        SoundEffect.FALL.play();
    }
}