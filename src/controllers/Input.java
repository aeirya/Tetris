package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import models.tetriminos.Tetrimino;

public class Input implements KeyListener {

    private ICommandReceiver executer;

    public Input(ICommandReceiver ip) {
        executer = ip;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        executer.receiveCommand( parse(e) );
    }
    
    private ICommand parse(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'a':
            return Tetrimino::moveLeft;
            case 'd':
            return Tetrimino::moveRight;
            default:
            case 'w':
            return Tetrimino::rotate;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { /* */ }
    
    @Override
    public void keyTyped(KeyEvent e) { /* */ }
}