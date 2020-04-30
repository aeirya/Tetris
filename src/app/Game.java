package app;

import controllers.manager.GameManager;
import controllers.state.GameState;
import controllers.input.IMenuCommand;
import controllers.input.Input;
import ui.graphics.IGameGraphics;
import ui.graphics.SwingGraphics;
import util.audio.GameAudioPlayer;
import util.audio.IGameAudioPlayer;
import util.file.save.GameSave;
import util.time.GameTimer;

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
    }

    public static Game getInstance() {
        return instance;
    }

    private void loadState(GameState state) {
        audioPlayer.reset(); 
        if (state!=null) util.log.GameLogger.debug("loading state: " + state.toString());
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
            state = manager.update(timer.isTickTime());
            if (!timer.isLocked()) {
                timer.queue(gameGraphics::redraw);
                timer.flush();
                timer.holdOn();
            }
            gameGraphics.update(state);
        } else {
            doWait();
        }
    }

    private void doWait() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            util.log.GameLogger.interrupted();
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
        }
        audioPlayer.togglePlay();
    }

    public void toggleMenu() {
        gameGraphics.toggleMenu();
    }
    
    public void toggleMute() {
        audioPlayer.toggleMute();
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
        this.save();
        Tetris.quitGame();
    }

    /** returns true is the command is null */
    public boolean receiveCommand(IMenuCommand cmd) {
        if (cmd==null) return true;
        cmd.act(this);
        return false;
    }
}