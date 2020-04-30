package controllers.state;

import controllers.level.Level;
import controllers.score.GameScore;
import models.tetrimino.Tetrimino;

public class ReadableGameState extends GameState {

    private static final long serialVersionUID = 9132967900992245147L;
    
    public ReadableGameState(Level level, Tetrimino current, Tetrimino next, GameScore score) {
        super(level, current, next, score);
    }

    public Level getLevel() {
        return this.level;
    }
    
    public Tetrimino getCurrent() {
        return this.current;
    }

    public Tetrimino getNext() {
        return this.next;
    }

    public GameScore getScore() {
        return this.score;
    }
}