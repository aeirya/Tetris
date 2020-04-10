package models;

import models.tetriminos.*;
import models.tetriminos.Tetrimino;

public class TetriminoGenerator {

    private TetriminoGenerator() {}

    public static Tetrimino random(int x, int y) {
        return new Tetrimino(new TShaped(), x, y);
    }

}