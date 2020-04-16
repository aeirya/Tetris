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
        checkLines();
    }

    public Tetrimino spawnTetrimino() {
        return builder.spawnTetrimino();
    }

    private void checkLines() {
        int i = 0;
        for (Box[] line : staticBoxes) {
            if (isLineFull(line)) {
                util.log.GameLogger.log("line full");
                deleteLine(line);
                dropLevel(i);
            }
            i++;
        }
    }

    private boolean isLineFull(Box[] line) {
        util.log.GameLogger.outdatedLog("is Line full: "+line);
        for(int i =0; i < line.length; i++) {
            if (line[i]==null) return false;
        }
        return true;
    }

    private void deleteLine(Box[] line) {
        for (int i = 0; i < line.length; i++) {
            line[i] = null;
        }
    }

    /** Moves any box above line i down */
    private void dropLevel(int n) {
        for (int i=n; i>=0 ; i--) {
            staticBoxes[i] = staticBoxes[i-1];
        }
    }
}