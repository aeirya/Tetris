package controllers;

import ui.drawlist.DrawList;

public class GameState {

    private DrawList gamePanel;

    public GameState( DrawList gamePanel ) {
        this.gamePanel = gamePanel;
    }

    public DrawList getGamePanelDrawables() {
        return gamePanel;
    }
}