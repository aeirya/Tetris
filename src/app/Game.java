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

    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        //Load some stuff maybe?
    }
    
    // runs on a loop by the tetris class
    public void update() {
        timer.queue(gameGraphics::redraw);
        timer.holdOn();
        Tetris.quitGame();
    }
}