package controllers;

import models.DrawList;
import models.TetriminoGenerator;
import models.tetriminos.Tetrimino;

public class GameManager {

    private final LevelDesigner level = new LevelDesigner();
    private DrawList gamePanelList = new DrawList();

    public GameManager() {
        Tetrimino t = TetriminoGenerator.random(2, 2);
        gamePanelList.add(t);
        gamePanelList.add(level.spawnWall());
    }

    public GameState update() {
        
        return new GameState(gamePanelList);
    }
}