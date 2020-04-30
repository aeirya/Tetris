package controllers.manager;

import app.Game;
import controllers.input.ICommandReceiver;
import controllers.input.ICommand;
import controllers.state.Backup;
import controllers.score.GameScore;
import controllers.score.TopScoreManager;
import controllers.state.GameState;
import controllers.state.ReadableGameState;
import models.tetrimino.Tetrimino;
import util.audio.SoundEffect;
import util.time.GameTimer;
import controllers.level.Level;

public class GameManager implements ICommandReceiver {

    private final Level level;
    private Tetrimino current;
    private Tetrimino next;
    private final Lock inputLock = new Lock(4);
    private final Lock fallLock = new Lock(1, SoundEffect.FELL::play);
    private final GameTimer timer;
    private final GameScore score;
    private final GameEventHandler event;
    private final Backup firefighter;
    
    public GameManager(GameTimer timer, GameState state) {
        firefighter = new Backup(state);
        event = new GameEventHandler();
        if (state == null) {
            level = new Level();
            current = null;
            next = null;
            score = new GameScore();
        } else {
            util.log.GameLogger.log("loading from save");
            final ReadableGameState data = (ReadableGameState) state.get(this);
            level = data.getLevel();
            current = data.getCurrent();
            next = data.getNext();
            score = data.getScore().applyRevivePentaly();
        }
        this.timer = timer;
        timer.resetSpeed();
    }

    /** @return next game state */
    public GameState update(boolean isTick) {
        if (current == null) {
            util.log.GameLogger.outdatedLog("null current");
            event.call(GameEvent.SPAWN);
            backup();
        }
        if (level.checkCollision(current)) {
            event.collision();
        }
        if (isTick) {
            inputLock.unlock();
            if (fallLock.isUnlocked()) { 
                event.applyGravity();
            }
            else {
                try{
                    event.call(GameEvent.END_ROUND);
                } catch(Exception e) {
                    firefighter.hold(null); //pushing the history
                    event.call(GameEvent.GAMEOVER);
                }
            }
        }
        return updatedGameState(level, current, next, score );
    }
    
    private void backup() {
        util.log.GameLogger.outdatedLog("backing up");
        firefighter.hold(
            updatedGameState(level, current, next, score)
        );
    }

    public GameState restore() {
        return firefighter.restore();
    }

    private GameState updatedGameState(Level level, Tetrimino current, Tetrimino next, GameScore score) {
        return new GameState(level, current, next, score);
    }

    @Override
    public void receiveCommand(ICommand cmd) {
        if (inputLock.isUnlocked() && fallLock.isUnlocked()) {
            fallLock.unlock();
            cmd.act(current);
        }
    }

    private static class Lock {
        
        private int counter = 0;
        private final int limit;

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

    private enum GameEvent {
        LINE_REMOVE,
        END_ROUND,
        SPAWN,
        GAMEOVER
    }

    private class GameEventHandler {

        private void call(GameEvent event) {
            get(event).run();
        }

        private Runnable get(GameEvent event) {
            switch(event) {
                case GAMEOVER:
                return this::gameOver;
                case LINE_REMOVE:
                return this::lineRemove;
                case END_ROUND:
                return this::endRound;
                case SPAWN:
                return this::spawn;
                default:
                return () -> {};
            }
        }

        //hard to say goodbye :D
        private void gameOver() {
            util.log.GameLogger.log("\u001B[31m"+"game over?"+"\u001B[0m");
            SoundEffect.GAMEOVER.play();
            Game.getInstance().togglePause();
            TopScoreManager.getInstance().addScore(score);
        }

        private void endRound() {
            timer.resetSpeed();
            level.digest(current);
            this.call(GameEvent.LINE_REMOVE);
            current = null;
            score.nextLevel();
        }
        
        private void lineRemove() {
            final int toRemove = level.checkLines();
            if (toRemove > 1) SoundEffect.STACK.play();
            if (toRemove > 0) SoundEffect.EXPLOSION.play();
            score.removedLine( toRemove );
        }
        
        //physics :B
        private void collision() {
            util.log.GameLogger.outdatedLog("collision!");
            current.stopAnimation();
            inputLock.report();
            current.revert();
        }
        
        private void applyGravity() {
            current.fall();
            if (level.checkCollision(current)) {
                fallLock.report();
                util.log.GameLogger.outdatedLog("fell!");
                current.revert();
            }
        }    

        //spawning functions        
        private Tetrimino spawnTetrimino() {
            if (next == null) next = level.generateTetrimino();
            return level.spawnTetrimino(next);
        }

        private void spawn() {
            current = spawnTetrimino();
            next = level.generateTetrimino();
            fallLock.unlock();
            util.log.GameLogger.outdatedLog("spawn finished");
        }
    }
}

