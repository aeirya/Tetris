package app;

import java.awt.EventQueue;

public class Tetris {
    private static boolean quit = false;

    public static void main(String[] args) {
        Game game = Game.getInstance();
        EventQueue.invokeLater( 
                game::start
            );  
        while (!quit) game.update();
    }

    public static void quitGame() {
        Tetris.quit = true;
    }
}