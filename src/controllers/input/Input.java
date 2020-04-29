package controllers.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.Game;
import models.tetrimino.Tetrimino;

/** Responsible for getting keybaord input and passing it to its executer @see @ICommandReceiver */

public class Input implements KeyListener {

    private ICommandReceiver executer;

    public Input(ICommandReceiver ip) {
        executer = ip;
    }

    public void setTo(ICommandReceiver ip) {
        executer = ip;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        parse(e);
    }
    
    private void parse(KeyEvent e) {
        if (
            Game.getInstance()
            .receiveCommand( parseMenuCommand(e) )
        ) {
            executer.receiveCommand( parseTetriminoCommand(e) );
        }
    }

    private ICommand parseTetriminoCommand(KeyEvent e) {
        util.log.GameLogger.outdatedLog("keychar: "+e.getKeyChar()+" , keycode: "+e.getKeyCode());
        
        if (e.getKeyCode()==32)  //space
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
    
    private IMenuCommand parseMenuCommand(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'l':
            return Game::load;
            case 'r':
            return Game::reset;
            case 'm':
            return Game::toggleMenu;
            case 'k':
            return Game::save;
            case 'y':
            return Game::restore;
            case 'p':
            return Game::quit;
            default:
            return null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { /*_*/ }
    
    @Override
    public void keyTyped(KeyEvent e) { /*v*/ }
}