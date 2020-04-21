package controllers;

import controllers.level.Level;
import models.tetrimino.Tetrimino;
import ui.drawlist.DrawList;

public class GameState {

    private Level level;
    private Tetrimino current;
    private Tetrimino next;

    public GameState( Level level, Tetrimino current, Tetrimino next) {
        this.level = level;
        this.current = current;
        this.next = next;
    }

    public DrawList getGamePanelDrawables() {
        return new DrawList().add(level).add(current);
    }

    public DrawList getSidePanelDrawables() {
        return new DrawList().add(next);
    }
}