package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.IGameObject;
import models.tetriminos.Tetrimino;
import ui.Architect.Box;

public class Level {

    private final LevelDesigner builder = new LevelDesigner();
    private Box[][] staticBoxes;

    public Level() {
        staticBoxes = builder.create2DBoxList();
    }

    public List<Box> build() {
        List<Box> level = new ArrayList<>();
        List<Box> wallsList = Arrays.asList(builder.spawnWall());
        List<Box> background = Arrays.asList(builder.spawnBackgroundPixels());
        digest(wallsList);
        level.addAll(wallsList);
        level.addAll(background);
        return level;
    }

    public boolean checkCollision(Tetrimino t) {
        return t.collides(staticBoxes);
    }

    private void digest(List<Box> items) {
        for (Box box : items) {
            box.addTo(staticBoxes);
        }
    }

    public void digest(IGameObject go) {
        go.addTo(staticBoxes);
    }

    public Tetrimino spawnTetrimino(Tetrimino lastSpawned) {
        // if (lastSpawned!=null) lastSpawned.addTo(map);
        return builder.spawnTetrimino();
    }
}