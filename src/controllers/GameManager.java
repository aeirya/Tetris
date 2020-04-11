package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.tetriminos.Tetrimino;

public class GameManager {

    private final LevelDesigner level = new LevelDesigner();
    private DrawList gamePanelList = new DrawList();
    private List<Tetrimino> tetriminos= new ArrayList<>();
    private Tetrimino current = null;

    public GameManager() {
        gamePanelList.add(level.spawnWall());
    }
    
    private Tetrimino spawn() {
        Tetrimino spawned = level.spawnTetrimino();
        tetriminos.add(spawned);
        gamePanelList.add(spawned);
        return spawned;
    }

    public GameState update() {
        if (current==null) current = spawn();
        current.fall();
        return updatedGameState();
    }
    
    private GameState updatedGameState() {
        return new GameState(gamePanelList);
    }

    private void applyGravity() {
        for (Tetrimino t : tetriminos) {
            t.fall();
        }
    }
}