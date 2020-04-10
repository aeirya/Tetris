package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.TetriminoGenerator;
import models.TetriminoGenerator.TetriminoShape;
import models.tetriminos.Tetrimino;

public class GameManager {

    private final LevelDesigner level = new LevelDesigner();
    private DrawList gamePanelList = new DrawList();
    private List<Tetrimino> tetriminos= new ArrayList<>();

    private Tetrimino t;
    
    public GameManager() {
        gamePanelList.add(level.spawnWall());
        t = TetriminoGenerator.random(2, 2);
        Tetrimino t2 = TetriminoGenerator.create(TetriminoShape.S, 6,16);
        Tetrimino t3 = TetriminoGenerator.create(TetriminoShape.L, 6,4);
        tetriminos.add(t);
        tetriminos.add(t2);
        tetriminos.add(t3);
        tetriminos.add(TetriminoGenerator.create(TetriminoShape.O, 2,10));
        tetriminos.add(TetriminoGenerator.create(TetriminoShape.Z, 7,10));
        tetriminos.add(TetriminoGenerator.create(TetriminoShape.I, 2,16));
        gamePanelList.add(tetriminos);
    }

    public GameState update() {
        // t.fall();
        // t.rotate();
        for (int i=0; i < tetriminos.size(); i++) {
            tetriminos.get(i).rotate();
        }

        return new GameState(gamePanelList);
    }
}