package app;

import controllers.GameManager;
import controllers.GameState;
import controllers.input.Input;
import ui.graphics.IGameGraphics;
import ui.graphics.SwingGraphics;
import util.time.GameTimer;

/**
 * Game
 */
public class Game {
    
    private static final Game instance = new Game();
    private final IGameGraphics gameGraphics = new SwingGraphics();
    private final GameTimer timer = GameTimer.getInstance();
    private final GameManager manager = new GameManager(timer);

    private Game() {}
    
    public static Game getInstance() {
        return instance;
    }
    
    public void start() {
        final GameSettings settings = new GameSettings("settings.properties");
        final Input input = new Input(manager);
        gameGraphics.setup(settings, input);
    }
    
    /** runs on a loop by the tetris class, responsible for generating a new game state and passing it to game graphics */
    public void update() {
        final GameState state = manager.update(timer.isTickTime());
        if (!timer.isLocked()) {    
            timer.queue(gameGraphics::redraw);
            timer.flush();
            timer.holdOn();
        }
        gameGraphics.update(state);
    }

    public void changeGameSpeed() {
        timer.goFaster();
    }
}