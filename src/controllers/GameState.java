package controllers;

import controllers.level.Level;
import models.tetrimino.Tetrimino;
import ui.drawlist.DrawList;
import ui.panels.ControlPanel;
import ui.panels.GamePanel;
import ui.panels.NextPanel;
import ui.panels.ScorePanel;
import ui.panels.SidePanel;

public class GameState {

    private Level level;
    private Tetrimino current;
    private Tetrimino next;
    private GameScore score;
    private boolean isMute;
    private boolean isPaused;

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
        if (receiver.getClass()==SidePanel.class) {
            return null;
        }
        if (receiver.getClass()==ScorePanel.class) {
            return score;
        }
        if (receiver.getClass()==ControlPanel.class) {
            return new Boolean[] { isMute, isPaused };
        }
        return null;
    }

    public DrawList getGamePanelDrawables() {
        return new DrawList().add(level).add(current);
    }

    public DrawList getNextPanelDrawables() {
        return new DrawList().add(next);
    }
}