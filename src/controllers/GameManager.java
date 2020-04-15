package controllers;

import java.util.List;

import app.Tetris;
import models.DrawList;
import models.Drawable;
import models.tetriminos.Tetrimino;
import util.time.GameTimer;

public class GameManager implements ICommandReceiver {

    private final Level level = new Level();
    private DrawList gamePanelList = new DrawList();
    private Tetrimino current = null;
    private final Lock inputLock = new Lock(4);
    private final Lock fallLock = new Lock(1);
    private GameTimer timer;
    List<Tetrimino> aspawnedMinos;

    public GameManager(GameTimer timer) {
        this.timer = timer;
        gamePanelList.add(level.build());
    }
    
    private Tetrimino spawn() {
        Tetrimino spawned = level.spawnTetrimino(current);
        gamePanelList.add((Drawable)spawned);
        fallLock.unlock();
        timer.resetSpeed();
        return spawned;
    }

    /** @return next game state */
    public GameState update(boolean isTick) {
        if ( current == null ) {
            current = spawn();
        }
        if (isTick) {
            inputLock.unlock();
            if (fallLock.isUnlocked()) { 
                applyGravity();
            }
            else {
                try{
                    level.digest(current);
                } catch(Exception e) {
                    util.log.GameLogger.log("\u001B[31m"+"game over?"+"\u001B[0m");
                    Tetris.quitGame();
                }
                current = spawn(); //TODO: add this to try catch later
            }
        }
        if (level.checkCollision(current)) {
            current.stopAnimation();
            inputLock.report();
            util.log.GameLogger.outdatedLog("collision!");
            current.revert();
        }
        return updatedGameState(gamePanelList);
    }
    
    private GameState updatedGameState(DrawList gamePanelList) {
        return new GameState(gamePanelList);
    }

    private void applyGravity() {
        current.fall();
        if (level.checkCollision(current)) {
            fallLock.report();
            util.log.GameLogger.outdatedLog("fell!");
            current.revert();
        }
        //TODO: if lineRemoved others.fall()
    }

    @Override
    public void receiveCommand(ICommand cmd) {
        if (inputLock.isUnlocked() && fallLock.isUnlocked()){
            cmd.act(current);
            fallLock.unlock();
        }
    }

    public static class Lock {
        
        private int counter = 0;
        private int limit = 1;

        public Lock(int limit) {
            this.limit = limit;
        } 

        public void report() { 
            counter += 1;
        }

        public boolean isUnlocked() { return counter < limit; }

        public void unlock() { counter = 0; }
    }
}

