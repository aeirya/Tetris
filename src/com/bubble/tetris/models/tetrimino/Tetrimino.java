package com.bubble.tetris.models.tetrimino;

import com.bubble.tetris.controllers.animation.Animation;
import com.bubble.tetris.controllers.animation.Animator;
import com.bubble.tetris.ui.drawlist.CollidableDrawList;
import com.bubble.tetris.util.audio.SoundEffect;
import com.bubble.tetris.models.GameObject;
import com.bubble.tetris.models.interfaces.Animate;
import com.bubble.tetris.models.interfaces.Drawable;
import com.bubble.tetris.models.interfaces.IGameObject;
import com.bubble.tetris.models.interfaces.IShape;
import java.awt.Graphics;
import java.util.List;

import com.bubble.tetris.app.Game;
import com.bubble.tetris.models.level.map.Map;
import com.bubble.tetris.models.Architect;
import java.awt.Color;

public class Tetrimino implements IGameObject, IShape, Drawable, Animate {

    private static final long serialVersionUID = 1L;
    
    private final IGameObject body;
    private final IShape shape;
    
    private boolean isLastActionMove = true;
    private transient CollidableDrawList leonardoDaVinci = null;
    
    private transient Animator animator = new Animator();
    private boolean isHidden = false;

    public Tetrimino(final IShape shape, final int x, final int y, final Color color) {
        this(shape, Architect.getInstance().new Box(x, y, color));
        com.bubble.tetris.util.log.GameLogger.outdatedLog("Successfully created the tetrimino");
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
    
    public void toggleHidden() {
        leonardoDaVinci.toggleHidden();
    }

    public void show() {
        leonardoDaVinci.show();
    }

    public void draw(final Graphics g) {
        if (!isHidden) {
            if(leonardoDaVinci!=null) {
                leonardoDaVinci.draw(g);
            }
            else {
                com.bubble.tetris.util.log.GameLogger.outdatedLog("sending our best ninjas to wake up duh vinci :p");
                update(); 
                this.draw(g);
            }
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
        return new Tetrimino(shape, body);
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

    public void setAnimation(final Animation animation) {
        if (animator == null) animator = new Animator();
        animator.setAnimation(animation);
        animator.playAnimation(this);
    }

    public void stopAnimation() {
        try { 
            animator.stopAnimation();
        } catch(Exception ex) {
            com.bubble.tetris.util.log.GameLogger.debug("ok what animation to stop? no animator :|");
        }
    }

    public String toString() {
        return shape.toString() + body.toString();
    }
}