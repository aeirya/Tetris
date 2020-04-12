package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    private void digest(List<Box> items) {
        for (Box box : items) {
            box.addTo(staticBoxes);
        }
    }

    public Tetrimino spawnTetrimino() {
        return builder.spawnTetrimino();
    }

    public Box[][] getBoxes() {
        return staticBoxes;
    }
}