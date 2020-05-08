package com.bubble.tetris.controllers.input;

import com.bubble.tetris.app.Game;

public interface IMenuCommand {
    void act(Game gameInstance);
}