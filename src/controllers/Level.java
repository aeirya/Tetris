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
        for (int i=0; i<staticBoxes.length-1; i++) {
            Box[] line = staticBoxes[i];
            util.log.GameLogger.outdatedLog("is Line full: "+i);
            if (isLineFull(line)) {
                util.log.GameLogger.log("line full");
                deleteLine(line);
                dropLevel(i);
            }
        }
    }

    private boolean isLineFull(Box[] line) {
        for(int i = 1; i < line.length-1; i++) {   
            if (line[i]==null) return false;
        }
        return true;
    }

    private boolean isLineEmpty(Box[] line) {
        for (int i = 1; i < line.length-1; i++) {
            // System.out.println("Iterating" + i);
            if (line[i]!=null) return false;
        }
        return true;
    }

    private void deleteLine(Box[] line) {
        for (int i = 1; i < line.length-1; i++) {
            line[i] = null;
        }
    }

    /** Moves any box above line i down */
    private void dropLevel(int n) {
        for (int i=n-1; i>=0 ; i--) {
            Box[] line = staticBoxes[i];
            if (isLineEmpty(line)) { break; }
            staticBoxes[i+1] = line;
            moveLine(line);
        }
    }

    private void moveLine(Box[] line) {
        for (Box box : line) {
            if(box != null) box.fall();
        }
    }
}