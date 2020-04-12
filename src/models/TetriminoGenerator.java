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
            TColor.get((TColor) chooseRandom(TColor.getUsableColors()))
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

        RED, GREEN, BLUE, YELLOW, CYAN, PURPLE, BACKGROUND_BLACK, WALL_GREY, RANDOM;

        private static int a = 150;
        private static int b = 50;

        public static Color get(TColor color) {
            switch (color) {
                default:
                case RED:
                return new Color(a,b,b);
                case GREEN:
                return new Color(b,a,b);
                case BLUE:
                return new Color(b,b,a);
                case PURPLE:
                return new Color(a,b,a);
                case CYAN:
                return new Color(b,a,a);
                case YELLOW:
                return new Color(a,a,b);
                case BACKGROUND_BLACK:
                return new Color(40,40,45);
                case WALL_GREY:
                return new Color(100, 100, 100);
                case RANDOM:
                int a = rand.nextInt(256);
                int b = rand.nextInt(256);
                int c = rand.nextInt(256);
                util.log.GameLogger.log("Generated color: "+a+","+","+b+","+c);
                return new Color(a,b,c);
            }
        } 

        public static TColor[] getUsableColors() {
            return new TColor[]{ RED, GREEN, BLUE, YELLOW, CYAN, PURPLE } ;
        }

        //for testing
        public static void setColorTone(int a, int b) {
            TColor.a = a;
            TColor.b = b;
        }
    }
}
