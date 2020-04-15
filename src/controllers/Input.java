package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import models.tetriminos.Tetrimino;

/** Responsible for getting keybaord input and passing it to its executer @see @ICommandReceiver */

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
        util.log.GameLogger.outdatedLog("keychar: "+e.getKeyChar()+" , keycode: "+e.getKeyCode());
        if (e.getKeyCode()==1234) //this is to change
            return (Tetrimino t) -> t.setAnimation(Tetrimino::fall);
        if (e.getKeyCode()==32) 
            return Tetrimino::dash; 
        switch(e.getKeyChar()) {
            case 'a':
            return Tetrimino::moveLeft;
            case 'd':
            return Tetrimino::moveRight;
            default:
            case 'w':
            return Tetrimino::rotateLeft;
            case 's':
            return Tetrimino::rotateRight;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { /* */ }
    
    @Override
    public void keyTyped(KeyEvent e) { /* */ }
}