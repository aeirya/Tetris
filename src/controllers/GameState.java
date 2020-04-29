package controllers;

import controllers.level.Level;
import models.tetrimino.Tetrimino;
import ui.drawlist.DrawList;
import ui.panels.ControlPanel;
import ui.panels.GamePanel;
import ui.panels.NextPanel;
import ui.panels.ScorePanel;
import ui.panels.SidePanel;

public class GameState implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    protected final Level level;
    protected final Tetrimino current;
    protected final Tetrimino next;
    protected final GameScore score;

    public GameState( Level level, Tetrimino current, Tetrimino next, GameScore score) {
        this.level = level;
        this.current = current;
        this.next = next;
        this.score = score;
    }

    public Object get(Object receiver) {
        if (receiver.getClass()==GamePanel.class) {
            return getGamePanelDrawables();
        }
        if (receiver.getClass()==NextPanel.class) {
            return getNextPanelDrawables();
        }
        if (receiver.getClass()==ScorePanel.class) {
            return score;
        }
        if (receiver.getClass()==SidePanel.class) {
            return null;
        }
        if (receiver.getClass()==ControlPanel.class) {
            return null;
        }
        if (receiver.getClass()==GameManager.class) {
            return new ReadableGameState(level, current, next, score);
        }
        return null;
    }

    public DrawList getGamePanelDrawables() {
        return new DrawList().add(level).add(current);
    }

    public DrawList getNextPanelDrawables() {
        return new DrawList().add(next);
    }

    public String toString() {
        return
            "Printing game state" + 
            "\n\tCurrent: " + ( (current != null) ? current.toString() : null ) +
            "\n\tNext: " + ( (next != null) ? next.toString() : null ) +
            "\n\tScore: " + score.getScore()
        ;
    }
}