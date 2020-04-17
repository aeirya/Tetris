package controllers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Drawable;
import models.IGameObject;
import models.tetriminos.Tetrimino;
import ui.Architect.Box;

public class Level implements Drawable {

    private final LevelDesigner builder = new LevelDesigner();
    private Map map;

    public Level() {
        map = new Map(builder.create2DBoxList());
    }

    public List<Box> build() {
        List<Box> level = new ArrayList<>();
        level.addAll(
            Arrays.asList(builder.spawnWall())
        );
        level.addAll(
            Arrays.asList(builder.spawnBackgroundPixels())
        );
        return level;
    }

    public boolean checkCollision(Tetrimino tetrimino) {
        return tetrimino.collides(map);
    }

    public Tetrimino spawnTetrimino() {
        return builder.spawnTetrimino();
    }

    public void digest(IGameObject go) {
        map.digest(go);
    }

    public void checkLines() {
        map.checkLines();
    }

    public void draw(Graphics g) {
        map.draw(g);
    }
}