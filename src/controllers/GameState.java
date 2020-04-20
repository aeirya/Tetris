package controllers;

import models.interfaces.Drawable;
import models.tetrimino.Tetrimino;
import ui.drawlist.DrawList;

public class GameState {

    private DrawList gamePanel;
    private Tetrimino next;

    public GameState( DrawList gamePanel, Tetrimino next) {
        this.gamePanel = gamePanel;
        this.next = next;
    }

    public DrawList getGamePanelDrawables() {
        return gamePanel;
    }

    public DrawList getSidePanelDrawables() {
        return new DrawList().add((Drawable) next);
    }
}