package models;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import models.tetriminos.Tetrimino;
import ui.Architect.Box;

public class Shape implements IShape {

    protected ShapeCoordinate[] coordinates;
    private boolean isChanged  = true;

    public void rotate() {
        for (ShapeCoordinate c : coordinates) {
            c.rotate();
        }
        isChanged = true;
    }

    public Drawable[] applyShape(IGameObject object) {
        if (isChanged) {
            List<Drawable> result = new ArrayList<>();
            for (Coordinate c : coordinates) {
                object.copy().move(c);
            }
            isChanged = false;
        }
    }

    /** Return's the i'th box */
    public Coordinate getCoordinate(int i) {
        return i < coordinates.length ? coordinates[i] : new Coordinate();
    }
 
    /** It has the ability to rotate, and has a method for Shape, getting coordinates at the same time */
    protected static class ShapeCoordinate extends Coordinate {

        private int x;
        private int y;

        public ShapeCoordinate(int x, int y) {
            super(x,y);
        }

        /** Rotates right 90 degrees */
        public void rotate() {
            int t = x;
            x = y;
            y = -1 * t;
        }

        /** For making life eaiser! Easily import coordinates. How to use: makeList(x1, y1, x2, y2, ...) */
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