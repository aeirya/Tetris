package controllers.input;

import models.tetrimino.Tetrimino;

@FunctionalInterface
public interface ICommand {
    void act(Tetrimino t);
}