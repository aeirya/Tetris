package com.bubble.tetris.controllers.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bubble.tetris.app.Game;
import com.bubble.tetris.models.tetrimino.Tetrimino;

/** Responsible for getting keybaord input and passing it to its executer @see @ICommandReceiver */

public class Input implements KeyListener {

    private ICommandReceiver executer;

    public Input(final ICommandReceiver ip) {
        executer = ip;
    }

    public void setTo(final ICommandReceiver ip) {
        executer = ip;
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        parse(e);
    }
    
    private void parse(final KeyEvent e) {
        if (
            Game.getInstance()
            .receiveCommand( parseMenuCommand(e) )
        ) {
            executer.receiveCommand( parseTetriminoCommand(e) );
        }
    }

    private ICommand parseTetriminoCommand(final KeyEvent e) {
        com.bubble.tetris.util.log.GameLogger.outdatedLog("keychar: "+e.getKeyChar()+" , keycode: "+e.getKeyCode());
        
        //God mode! having fun with the mechanics :p 
        if (Game.getInstance().isPaused()) {
            char key = e.getKeyChar();
            if(key == 'w') return Tetrimino::ascend;
            if(key == 's') return Tetrimino::fall;
            if(key == 'e') return Tetrimino::rotateRight;
            if(key == 'q') return Tetrimino::rotateLeft;
            if(key == 'f') return Tetrimino::toggleHidden;
        }

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
    
    private IMenuCommand parseMenuCommand(final KeyEvent e) {
        if (e.getKeyCode()==17) //control
            return Game::changeGameSpeed;

        switch(e.getKeyChar()) {
            case 'l':
            return Game::load;
            case 'r':
            return Game::reset;
            case 'm':
            return Game::toggleMenu;
            case 'v':
            return Game::save;
            case 'y':
            return Game::restore;
            case 't':
            return Game::quit;
            case 'p':
            return Game::togglePause;
            case 'u':
            return Game::toggleMute;
            default:
            return null;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) { /*_*/ 
        if(e.getKeyCode()==17) //control
            Game.getInstance().resetGameSpeed();
    }
   
    @Override
    public void keyTyped(final KeyEvent e) { /*v*/ }
}