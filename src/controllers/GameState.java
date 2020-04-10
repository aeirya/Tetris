package controllers;

import models.DrawList;

public class GameState {

    private DrawList gamePanel;

    public GameState( DrawList gamePanel ) {
        this.gamePanel = gamePanel;
    }

    public DrawList getGamePanelDrawables() {
        return gamePanel;
    }
}