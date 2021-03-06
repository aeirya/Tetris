package com.bubble.tetris.models.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bubble.tetris.models.level.map.Map;
import com.bubble.tetris.models.interfaces.Drawable;
import com.bubble.tetris.models.interfaces.IGameObject;
import com.bubble.tetris.models.tetrimino.Tetrimino;
import com.bubble.tetris.ui.drawlist.DrawList;
import com.bubble.tetris.models.Architect.Box;

public class Level implements Drawable, java.io.Serializable {

    private static final long serialVersionUID = 1L;
  
    private transient LevelDesigner builder = null;
    private Map map;
    private transient List<Box> myLevel = null;
    
    public Level() {
        builder = new LevelDesigner();
        map = new Map(builder.create2DBoxList());
    }

    public List<Box> build() {
        if (myLevel == null) {
            myLevel = new ArrayList<>();
            if (builder == null) 
                this.builder = new LevelDesigner();
            myLevel.addAll(
                Arrays.asList(builder.spawnWall())
            );
            myLevel.addAll(
                Arrays.asList(builder.spawnBackgroundPixels())
            );
        }
        return myLevel;
    }

    public boolean checkCollision(Tetrimino tetrimino) {
        return tetrimino.collides(map);
    }

    public Tetrimino generateTetrimino() {
        return builder.generateTetrimino();
    }

    public Tetrimino spawnTetrimino(Tetrimino tetrimino) {
        Tetrimino t = builder.spawnTetrimino(tetrimino);
        if (t.collides(map)) t = builder.spawnTetrimino(tetrimino);
        return t;
    }

    public void digest(IGameObject go) {
        map.digest(go);
    }

    /** checks and removes lines
     * @return lines removed */
    public int checkLines() {
        return map.checkLines();
    }

    public void draw(Graphics g) {
        new DrawList().add(build()).draw(g);
        map.draw(g);
    }

    public void refresh() {
        map.refresh();
    }
}