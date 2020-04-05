package app;

import ui.IGameGraphics;
import ui.SwingGraphics;

/**
 * Game
 */
public class Game {
    
    private static final Game instance = new Game();
    private final IGameGraphics gameGraphics = new SwingGraphics();
    
    private Game() {}

    public static Game getInstance() {
        return instance;
    }

    public void start() {
        // Start Timer maybe?
    }
    
    //runs on a loop
    public void update() {

        gameGraphics.redraw();
        // holdOn();
    }
}