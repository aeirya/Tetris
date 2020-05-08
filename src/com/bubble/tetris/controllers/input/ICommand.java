package com.bubble.tetris.controllers.input;

import com.bubble.tetris.models.tetrimino.Tetrimino;

@FunctionalInterface
public interface ICommand {
    void act(Tetrimino t);
}