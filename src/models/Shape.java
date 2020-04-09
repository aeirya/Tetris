package models;

import java.awt.Point;
import java.awt.Rectangle;

import models.tetriminos.Tetrimino;
import ui.Architect.Box;

public class Shape implements IShape {

    protected ShapeCoordinate[] coordinates;

    public void rotate() {
        for (ShapeCoordinate c : coordinates) {
            c.rotate();
        }
    }

    protected static class ShapeCoordinate extends Coordinate {

        private int x;
        private int y;

        public ShapeCoordinate(int x, int y) {
            super(x,y);
        }

        public void rotate() {
            int t = x;
            x = y;
            y = -1 * t;
        }

        public static ShapeCoordinate[] makeList(int... list) {
            ShapeCoordinate[] c = new ShapeCoordinate[list.length / 2];
            for (int i = 0; i < list.length; i += 2) {
                c[i / 2] = new ShapeCoordinate(i, i + 1);
            }
            return c;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

}