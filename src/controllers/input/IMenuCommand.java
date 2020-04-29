package controllers.input;

import app.Game;

public interface IMenuCommand {
    void act(Game gameInstance);
}