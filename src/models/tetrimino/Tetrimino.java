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

    private static final long serialVersionUID = 1L;
    
    private final IGameObject body;
    private final IShape shape;
    
    private boolean isLastActionMove = true;
    private transient CollidableDrawList leonardoDaVinci = null;
    
    private final transient Animator animator = new Animator();

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
    
    private void update() {
        leonardoDaVinci = createDrawList();
    }

    private CollidableDrawList createDrawList() {
        return new CollidableDrawList(applyShape(body));
    }
    
    public List<IGameObject> applyShape(final IGameObject body) {
        return shape.applyShape(body);
    }
    
    public void draw(final Graphics g) {
        if(leonardoDaVinci!=null) {
            leonardoDaVinci.draw(g);
        }
        else {
            util.log.GameLogger.outdatedLog("sending our best ninjas to wake up duh vinci :p");
            update(); 
            this.draw(g);
        }
    }
    
    public void revert() {
        if (isLastActionMove)
            body.revert();
        else
            shape.revert();
        isLastActionMove = !isLastActionMove;
        update();
    }

    public IGameObject copy() {
        return new Tetrimino(shape, body.copy());
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

    public void dash() {
        Game.getInstance().changeGameSpeed();
        SoundEffect.DASH.play();
    }

    public boolean collides(final Map objects) {
        return leonardoDaVinci != null && leonardoDaVinci.collides(objects);
    }

    public void addTo(final Map map) {
        leonardoDaVinci.addTo(map);
    }

    public void setAnimation(final ICommand animation) {
        animator.setAnimation(animation);
        animator.playAnimation(this);
    }

    public void stopAnimation() {
        try { 
            animator.stopAnimation();
        } catch(Exception ex) {
            util.log.GameLogger.debug("ok what animation to stop? no animator :|");
        }
    }

    public String toString() {
        return shape.toString() + body.toString();
    }
}