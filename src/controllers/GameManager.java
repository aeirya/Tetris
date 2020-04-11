package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.TetriminoGenerator.TColor;
import models.tetriminos.Tetrimino;

public class GameManager implements IInputParser {

    private final LevelDesigner level = new LevelDesigner();
    private DrawList gamePanelList = new DrawList();
    private List<Tetrimino> tetriminos= new ArrayList<>();
    private Tetrimino current = null;
    private Input input;
    
    List<Tetrimino> spawnedMinos;

    public GameManager() {
        gamePanelList.add(level.spawnWall());
    }
    
    private Tetrimino spawn() {
        Tetrimino spawned = level.spawnTetrimino();
        spawnedMinos = level.spawnAllMinosTest();
        gamePanelList.add(spawnedMinos);
        // tetriminos.add(spawned);
        // gamePanelList.add(spawned);
        // tetriminos.addAll(minos);
        return spawned;
    }

    public GameState update() {
        if (current==null) current = spawn();
        gamePanelList.removeAll(spawnedMinos);
        spawnedMinos= level.spawnAllMinosTest();
        gamePanelList.add(spawnedMinos);
        
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

	public void addKeyListener(Input input) {
        this.input = input;
	}

    @Override
    public void receiveCommand(ICommand cmd) {
        // this.
    }
}