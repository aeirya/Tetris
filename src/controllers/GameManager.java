package controllers;

import app.Tetris;
import controllers.input.ICommandReceiver;
import controllers.input.ICommand;
import models.tetrimino.Tetrimino;
import util.audio.SoundEffect;
import util.time.GameTimer;
import controllers.level.Level;

public class GameManager implements ICommandReceiver {

    private final Level level = new Level();
    private Tetrimino current = null;
    private Tetrimino next = null;
    private final Lock inputLock = new Lock(4);
    private final Lock fallLock = new Lock(1, SoundEffect.FELL::play);
    private GameTimer timer;
    private GameScore score = new GameScore();
    
    public GameManager(GameTimer timer) {
        this.timer = timer;
    }
    
    private Tetrimino spawn() {
        if (next == null) next = level.generateTetrimino();
        Tetrimino spawned = level.spawnTetrimino(next);
        next = level.generateTetrimino();
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
            util.log.GameLogger.outdatedLog("collision!");
            current.stopAnimation();
            inputLock.report();
            current.revert();
        }
        if (isTick) {
            inputLock.unlock();
            if (fallLock.isUnlocked()) { 
                applyGravity();
            }
            else {
                try{
                    endRound();
                } catch(Exception e) {
                    util.log.GameLogger.log("\u001B[31m"+"game over?"+"\u001B[0m");
                    gameover();
                }
            }
        }
        return updatedGameState(level, current, next, score );
    }
    
    private GameState updatedGameState(Level level, Tetrimino current, Tetrimino next, GameScore score) {
        return new GameState(level, current, next, score);
    }

    private void endRound() {
        level.digest(current);
        final int toRemove = level.checkLines();
        if (toRemove > 1) SoundEffect.STACK.play();
        if (toRemove > 0) SoundEffect.EXPLOSION.play();
        score.removedLine( toRemove );
        current = spawn();
        score.nextLevel();
    }

    private void gameover() {
        SoundEffect.GAMEOVER.play();
        Tetris.quitGame();
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

    private static class Lock {
        
        private int counter = 0;
        private int limit = 1;

        private Runnable onLock;

        public Lock(int limit) {
            this.limit = limit;
        } 

        public Lock(int limit, Runnable onLock) {
            this(limit);
            this.onLock = onLock;
        }

        public void report() { 
            counter += 1;
            check();
        }

        private void check() {
            if (counter == limit && onLock != null) new Thread(onLock).start();
        }

        public boolean isUnlocked() { return counter < limit; }

        public void unlock() { counter = 0; }
    }
}

