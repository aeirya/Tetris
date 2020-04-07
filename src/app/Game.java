package app;

import ui.IGameGraphics;
import ui.SwingGraphics;
import util.time.GameTimer;

/**
 * Game
 */
public class Game {
    
    private static final Game instance = new Game();
    private final IGameGraphics gameGraphics = new SwingGraphics();
    private final GameTimer timer = GameTimer.getInstance();
    private final GameSettings settings = new GameSettings();

    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        gameGraphics.setup(settings);
    }
    
    // runs on a loop by the tetris class
    public void update() {
        util.log.GameLogger.log("looping");
        timer.queue(gameGraphics::redraw);
        timer.flush();
        timer.holdOn();
    }
}