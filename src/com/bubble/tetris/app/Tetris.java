package com.bubble.tetris.app;

import com.bubble.tetris.models.state.Backup;

import java.awt.EventQueue;


public class Tetris {
    private static boolean quit = false;

    public static void main(String[] args) {
        Game game = Game.getInstance();
        EventQueue.invokeLater( 
                game::reset
            );  
        while (!quit) game.update();
    }   

    public static void quitGame() {
        Tetris.quit = true;
        cleanFiles();
        System.exit(0);
    }

    private static void cleanFiles() {
        Backup.clear();
    }
}