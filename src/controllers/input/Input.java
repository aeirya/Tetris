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
<<<<<<< HEAD
=======
        if (e.getKeyCode()==17) //shift
            return Game::changeGameSpeed;

>>>>>>> 6ff0fc29557168cce340d897309dc5d1a08cf56f
        switch(e.getKeyChar()) {
            case 'l':
            return Game::load;
            case 'r':
            return Game::reset;
            case 'm':
            return Game::toggleMenu;
<<<<<<< HEAD
            case 'k':
            return Game::save;
            case 'y':
            return Game::restore;
            case 'p':
            return Game::quit;
=======
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
>>>>>>> 6ff0fc29557168cce340d897309dc5d1a08cf56f
            default:
            return null;
        }
    }

    @Override
<<<<<<< HEAD
    public void keyReleased(KeyEvent e) { /*_*/ }
=======
    public void keyReleased(KeyEvent e) { /*_*/ 
        if(e.getKeyCode()==17) //control
            Game.getInstance().resetGameSpeed();
    }
>>>>>>> 6ff0fc29557168cce340d897309dc5d1a08cf56f
    
    @Override
    public void keyTyped(KeyEvent e) { /*v*/ }
}