package controllers.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.interfaces.Drawable;
import models.interfaces.IGameObject;
import models.tetrimino.Tetrimino;
import ui.drawlist.DrawList;
import models.Architect.Box;

public class Level implements Drawable {

    private final LevelDesigner builder = new LevelDesigner();
    private Map map;
    private List<Box> myLevel;
    
    public Level() {
        map = new Map(builder.create2DBoxList());
        myLevel = build();
    }

    public List<Box> build() {
        if (myLevel == null) {
            myLevel = new ArrayList<>();
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
        return builder.spawnTetrimino(tetrimino);
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
        new DrawList().add(myLevel).draw(g);
        map.draw(g);
    }
}