package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.GameObject;
import models.tetriminos.Tetrimino;

public class GameManager implements ICommandReceiver {

    private final Level level = new Level();
    private DrawList gamePanelList = new DrawList();
    private Tetrimino current = null;
    
    List<Tetrimino> spawnedMinos;

    public GameManager() {
        gamePanelList.add(level.build());
    }
    
    private Tetrimino spawn() {
        Tetrimino spawned = level.spawnTetrimino();
        gamePanelList.add(spawned);
        return spawned;
    }

    public GameState update(boolean isTick) {
        if ( current == null ) current = spawn();
        if (isTick) {
            applyGravity();
        }
        // if (current.collides(level.getBoxes())) {
        //     current.revert();
        // }
        return updatedGameState();
    }
    
    private GameState updatedGameState() {
        return new GameState(gamePanelList);
    }

    private void applyGravity() {
        current.fall();
        //@TODO if(lineRemoved) others.fall()
    }

    @Override
    public void receiveCommand(ICommand cmd) {
        cmd.act(current);
    }
}