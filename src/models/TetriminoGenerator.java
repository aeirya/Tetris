package models;

import models.tetriminos.*;

public class TetriminoGenerator {

    private TetriminoGenerator() {}

    public static Tetrimino random(int x, int y) {
        return new Tetrimino(new TShaped(), x, y);
    }

    public static Tetrimino create(TetriminoShape shapeType, int x, int y) {
        IShape shape = TetriminoShape.get(shapeType);
        return new Tetrimino(shape, x, y);
    }
    
    public enum TetriminoShape {
        T, O, I, L, J, S, Z;
        
        public static IShape get(TetriminoShape shape) {
            switch (shape) {
                default:
                case T:
                return new TShaped();
                case I:
                return new IShaped();
                case O:
                return new OShaped();
                case Z:
                return new ZShaped();
                case S:
                return new SShaped();
                case L:
                return new LShaped();
                case J:
                return new JShaped();
            }
        }
    }
}
