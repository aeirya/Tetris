package models;

import java.awt.Point;
import java.awt.Rectangle;

import ui.Architect.Box;

public class Shape {

    private Box[] boxes;
    protected Coordinate[] coordinates;

    public void rotate() {
        for (Coordinate c : coordinates) {
            c.rotate();
        }
    }

    protected static class Coordinate {

        private int x;
        private int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void rotate() {
            int t = x;
            x = y;
            y = -1 * t;
        }

        public static Coordinate[] makeList(int... list) {
            Coordinate[] c = new Coordinate[list.length / 2];
            for (int i = 0; i < list.length; i += 2) {
                c[i / 2] = new Coordinate(i, i + 1);
            }
            return c;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

}