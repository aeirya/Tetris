package models;

public class Shape implements IShape {

    protected ShapeCoordinate[] coordinates;

    public void rotate() {
        for (ShapeCoordinate c : coordinates) {
            c.rotate();
        }
    }

    public DrawList applyShape(IGameObject object) {
        DrawList result = new DrawList();
        for (Coordinate c : coordinates) {
            result.add((Drawable)object.updatedCoordinates(c));
        }
        return result;
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
                c[i / 2] = new ShapeCoordinate(list[i], list[i + 1]);
            }
            return c;
        }
    }
}