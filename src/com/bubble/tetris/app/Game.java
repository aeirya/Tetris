package com.bubble.tetris.app;

import com.bubble.tetris.controllers.manager.GameManager;
import com.bubble.tetris.models.state.GameState;
import com.bubble.tetris.controllers.input.IMenuCommand;
import com.bubble.tetris.controllers.input.Input;
import com.bubble.tetris.ui.graphics.IGameGraphics;
import com.bubble.tetris.ui.graphics.SwingGraphics;
import com.bubble.tetris.util.CustomListener;
import com.bubble.tetris.util.audio.GameAudioPlayer;
import com.bubble.tetris.util.audio.IGameAudioPlayer;
import com.bubble.tetris.util.file.save.GameSave;
import com.bubble.tetris.util.time.GameTimer;

/**
 * Game
 */
public class Game {

    private static final Game instance = new Game();
    private final Input input;
    private final IGameGraphics gameGraphics = new SwingGraphics();
    private final GameTimer timer = GameTimer.getInstance();
    private final IGameAudioPlayer audioPlayer = new GameAudioPlayer();
    private GameManager manager;
    private GameState state;
    private boolean isPaused = false;

    private Game() {
        manager = new GameManager(timer, null);
        input = new Input(manager);
        final GameSettings settings = new GameSettings("settings.properties");
        gameGraphics.setup(settings, input);
        new UiUpdater(gameGraphics).start();
    }

    public static Game getInstance() {
        return instance;
    }

    private void loadState(GameState state) {
        audioPlayer.reset(); 
        if (state!=null) com.bubble.tetris.util.log.GameLogger.debug("loading state: " + state.toString());
        manager = new GameManager(timer, state);
        input.setTo(manager);
        resume();
    }

    /**
     * runs on a loop by the tetris class, responsible for generating a new game
     * state and passing it to game graphics
     */
    public void update() {
        if (!isPaused) {
            updateState();
            updateGraphics();
        } else {
            doWait();
        }
    }
    
    private void updateState() {
        state = manager.update(timer.isTickTime());
    }

    private void updateGraphics() {
        if (!timer.isLocked()) {
            timer.queue(gameGraphics::redraw);
            timer.flush();
            timer.holdOn();
        }
        gameGraphics.update(state);
    }

    private void doWait() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            com.bubble.tetris.util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    //game flow

    public void changeGameSpeed() {
        timer.goFaster();
    }

    public void resetGameSpeed(){
        timer.resetSpeed();
    }

    private void resume() {
        if (isPaused) togglePause();
    }

    public boolean isPaused() {
        return isPaused;
    }

    //Menu options

    public void togglePause() {
        isPaused = !isPaused;
        if (!isPaused) {
            audioPlayer.pause(); //resets its state
            timer.isTickTime(); //reseting the ticktime
        }
        audioPlayer.togglePlay();
    }

    public void toggleMenu() {
        gameGraphics.toggleMenu();
    }
    
    public void toggleMute() {
        audioPlayer.toggleMute();
    }

    public boolean isMute() {
        return audioPlayer.isMute();
    }

    public void reset() {
        loadState(null);
    }

    public void load() {
        loadState(GameSave.loadState());
    }

    public void save() {
        GameSave.saveState(state);
    }

    public void restore() {
        loadState(manager.restore());
    }

    public void quit() {
        Tetris.quitGame();
    }

    /** returns true is the command is null */
    public boolean receiveCommand(IMenuCommand cmd) {
        if (cmd==null) return true;
        cmd.act(this);
        return false;
    }

    //updates the game on pause state
    private class UiUpdater extends CustomListener<IGameGraphics> {
        private UiUpdater(IGameGraphics gameGraphics) {
            super(
                gameGraphics, 
                120, 
                g -> { if (Game.getInstance().isPaused()) g.paint(); }
            );
        }
    }
}