package app;

import controllers.GameManager;
import controllers.GameState;
import controllers.Input;
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
    private final GameManager manager = new GameManager();

    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        gameGraphics.setup(settings);
        final Input input = new Input();
        gameGraphics.addKeyListener(input);
        manager.addKeyListener(input);
    }
    
    // runs on a loop by the tetris class
    public void update() {
        // util.log.GameLogger.log("looping");
        GameState state = manager.update();
        gameGraphics.update(state);
        timer.queue(gameGraphics::redraw);
        timer.flush();
        timer.holdOn();
    }
}