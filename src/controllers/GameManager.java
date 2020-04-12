package controllers;

import java.util.ArrayList;
import java.util.List;

import models.DrawList;
import models.Drawable;
import models.GameObject;
import models.tetriminos.Tetrimino;

public class GameManager implements ICommandReceiver {

    private final Level level = new Level();
    private DrawList gamePanelList = new DrawList();
    private Tetrimino current = null;
    private int collisionCounter = 0;
    List<Tetrimino> aspawnedMinos;

    public GameManager() {
        gamePanelList.add(level.build());
    }
    
    private Tetrimino spawn() {
        Tetrimino spawned = level.spawnTetrimino();
        gamePanelList.add((Drawable)spawned);
        return spawned;
    }

    public GameState update(boolean isTick) {
        if ( current == null ) current = spawn();
        if (isTick) {
            InputLock.unlock();
            applyGravity();
            if (current.collides(level.getBoxes())) {
                util.log.GameLogger.debug("fell!");
                current.revert();
            }
        }
        else if (current.collides(level.getBoxes())) {
            InputLock.reportCollision();
            util.log.GameLogger.debug("collision!");
            current.revert();
        }
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
        if (InputLock.isUnlocked()){
            cmd.act(current);
        }
    }

    private static class InputLock {

        private static int collisionCount = 0;

        private InputLock() {} 

        public static void reportCollision() { 
            collisionCount += 1;
        }

        public static boolean isUnlocked() { return collisionCount < 3; }

        public static void unlock() { collisionCount = 0; }
    }
}

