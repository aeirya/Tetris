package app;

import java.awt.EventQueue;

public class Tetris {
    private static boolean quit = false;
    //TODO: add pause

    public static void main(String[] args) {
        Game game = Game.getInstance();
        EventQueue.invokeLater( 
                game::start
            );  
        while (!quit) game.update();
        game.stop();
    }   

    public static void quitGame() {
        Tetris.quit = true;
    }
}