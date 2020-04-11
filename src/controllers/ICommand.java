package controllers;

import models.tetriminos.Tetrimino;

@FunctionalInterface
public interface ICommand {
    void act(Tetrimino t);
}