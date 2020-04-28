package app;

import java.awt.EventQueue;


public class Tetris {
    private static boolean quit = false;
    //TODO: add pause

    public static void main(String[] args) {
        Game game = Game.getInstance();
        EventQueue.invokeLater( 
                game::reset
            );  
        while (!quit) game.update();
    }   

    public static void quitGame() {
        Tetris.quit = true;
        System.exit(0);
    }
}