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
        List<Box> wallsList = Arrays.asList(builder.spawnWall());
        List<Box> background = Arrays.asList(builder.spawnBackgroundPixels());
        // digest(wallsList);
        level.addAll(wallsList);
        level.addAll(background);
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

    private void digest(List<Box> items) {
        for (Box box : items) {
            digest(box);
        }
    }

    public void checkLines() {
        map.checkLines();
    }

    public void draw(Graphics g) {
        map.draw(g);
    }
}