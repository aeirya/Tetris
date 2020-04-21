package controllers;

import java.util.List;

import app.Tetris;
import controllers.input.ICommandReceiver;
import controllers.input.ICommand;
import ui.drawlist.DrawList;
import models.interfaces.Drawable;
import models.tetrimino.Tetrimino;
import util.time.GameTimer;
import controllers.level.Level;

public class GameManager implements ICommandReceiver {

    private final Level level = new Level();
    private DrawList gamePanelList = new DrawList();
    private Tetrimino current = null;
    private Tetrimino next = null;
    private final Lock inputLock = new Lock(4);
    private final Lock fallLock = new Lock(1);
    private GameTimer timer;
    List<Tetrimino> aspawnedMinos;

    public GameManager(GameTimer timer) {
        this.timer = timer;
        gamePanelList.add(level.build());
        gamePanelList.add(level);
    }
    
    private Tetrimino spawn() {
        if (next == null) next = level.generateTetrimino();
        Tetrimino spawned = level.spawnTetrimino(next);
        next = level.generateTetrimino();
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
        if (level.checkCollision(current)) {
            current.stopAnimation();
            inputLock.report();
            util.log.GameLogger.outdatedLog("collision!");
            current.revert();
        }
        if (isTick) {
            inputLock.unlock();
            if (fallLock.isUnlocked()) { 
                applyGravity();
            }
            else {
                try{
                    gamePanelList.remove(current);
                    level.digest(current);
                    level.checkLines();
                    current = spawn();
                } catch(Exception e) {
                    util.log.GameLogger.log(e.toString());
                    util.log.GameLogger.log("\u001B[31m"+"game over?"+"\u001B[0m");
                    Tetris.quitGame();
                }
            }
        }
        return updatedGameState(gamePanelList);
    }
    
    private GameState updatedGameState(DrawList gamePanelList) {
        return new GameState(gamePanelList, next);
    }

    private void applyGravity() {
        current.fall();
        if (level.checkCollision(current)) {
            fallLock.report();
            util.log.GameLogger.outdatedLog("fell!");
            current.revert();
        }
    }

    @Override
    public void receiveCommand(ICommand cmd) {
        if (inputLock.isUnlocked() && fallLock.isUnlocked()){
            fallLock.unlock();
            cmd.act(current);
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

