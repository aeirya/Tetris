package models;

import models.tetriminos.*;
import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class TetriminoGenerator {

    private static Random rand = null;

    private TetriminoGenerator() {
    }

    public static Tetrimino random(int x, int y) {
        return new Tetrimino(
            TetriminoShape.get(
                (TetriminoShape) chooseRandom(TetriminoShape.values())),
            x, y, 
            TColor.get((TColor) chooseRandom(TColor.values()))
            );
    }

    //ok what just happened here
    private static <T extends Enum<T>> Enum<T> chooseRandom(Enum<T>[] e) {
        if (TetriminoGenerator.rand == null) {
            try {
                TetriminoGenerator.rand = SecureRandom.getInstanceStrong();
            } catch (NoSuchAlgorithmException e1) {
                rand = new Random();
                util.log.GameLogger.warning("No such algorithm exception!");
            }
        }
        int i = rand.nextInt(e.length);
        return e[i];
    }

    public static Tetrimino create(TetriminoShape shapeType, int x, int y) {
        IShape shape = TetriminoShape.get(shapeType);
        return new Tetrimino(shape, x, y);
    }
    
    public static Tetrimino create(TetriminoShape shapeType, int x, int y, TColor c) {
        IShape shape = TetriminoShape.get(shapeType);
        return new Tetrimino(shape, x, y, TColor.get(c));
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

    public enum TColor {
        RED, GREEN, BLUE, YELLOW, CYAN, PURPLE;

        public static Color get(TColor color) {
            switch (color) {
                default:
                case RED:
                return new Color(170,30,30);
                case GREEN:
                return new Color(30,170,30);
                case BLUE:
                return new Color(30,30,170);
                case PURPLE:
                return new Color(170,30,170);
                case CYAN:
                return new Color(30,170,170);
                case YELLOW:
                return new Color(170,170,30);
            }
        }
    }
}
