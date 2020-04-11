package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.tetriminos.Tetrimino;

public class GameManager implements ICommandReceiver {

    private final LevelDesigner level = new LevelDesigner();
    private DrawList gamePanelList = new DrawList();
    private List<Tetrimino> tetriminos= new ArrayList<>();
    private Tetrimino current = null;
    
    List<Tetrimino> spawnedMinos;

    public GameManager() {
        gamePanelList.add(level.spawnWall());
        gamePanelList.add(level.spawnBackgroundPixels());

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

    @Override
    public void receiveCommand(ICommand cmd) {
        cmd.act(current);
    }
}