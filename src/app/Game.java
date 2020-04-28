package app;

import controllers.GameManager;
import controllers.GameState;
import controllers.input.Input;
import ui.graphics.IGameGraphics;
import ui.graphics.SwingGraphics;
import util.audio.GameAudioPlayer;
import util.audio.IGameAudioPlayer;
import util.file.GameSave;
import util.time.GameTimer;

/**
 * Game
 */
public class Game {

    private static final Game instance = new Game();
    private final IGameGraphics gameGraphics = new SwingGraphics();
    private final GameTimer timer = GameTimer.getInstance();
    private final IGameAudioPlayer audioPlayer = new GameAudioPlayer();
    private final GameManager manager;
    private boolean isPaused = false;

    private Game() {
        manager = new GameManager(timer, null);
    }

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        final GameSettings settings = new GameSettings("settings.properties");
        final Input input = new Input(manager);
        gameGraphics.setup(settings, input);
    }

    /**
     * runs on a loop by the tetris class, responsible for generating a new game
     * state and passing it to game graphics
     */
    public void update() {
        if (!isPaused) {
            final GameState state = manager.update(timer.isTickTime());
            if (!timer.isLocked()) {
                timer.queue(gameGraphics::redraw);
                timer.flush();
                timer.holdOn();
                GameSave.saveState(state);
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

    public void changeGameSpeed() {
        timer.goFaster();
    }

    public void togglePause() {
        isPaused = !isPaused;
        audioPlayer.togglePlay();
    }

    public void toggleMute() {
        audioPlayer.toggleMute();
    }
    //TODO: add gamestate backup : public GameState getLastState()
}