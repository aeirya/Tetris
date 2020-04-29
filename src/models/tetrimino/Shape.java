package models.tetrimino;

import java.util.ArrayList;
import java.util.List;

import models.Coordinate;
import models.interfaces.IGameObject;
import models.interfaces.IShape;

public class Shape implements IShape  {

    private static final long serialVersionUID = 1L;

    protected ShapeCoordinate[] coordinates;
    private transient Runnable revert = () -> {};

    public void rotate(int i) {
        for (ShapeCoordinate c : coordinates) {
            c.rotate(i);
        }
        revert = () -> rotate(-1*i);
    }

    public void revert() {
        revert.run();
    }

    public List<IGameObject> applyShape(IGameObject object) {
        List<IGameObject> result = new ArrayList<>();
        for (Coordinate c : coordinates) {
            result.add( object.updatedCoordinates(c) );
        }
        return result;
    }


    public String toString() {
        String classname = this.getClass().getName();
        String[] list = classname.split("\\.");
        return list[list.length-1];
    }

    /** It has the ability to rotate, and has a method for Shape, getting coordinates at the same time */
    protected static class ShapeCoordinate extends Coordinate {

        private static final long serialVersionUID = 1L;

        public ShapeCoordinate(int x, int y) {
            super(x,y);
        }

        /** Rotates right 90 degrees */
        public void rotate() {
            int t = x;
            x = y;
            y = -1 * t;
        } 
        //@TODO: should use rotate(i) ?

        private void rotate(int i) {
            int t = x;
            x = i * y;
            y = -1 * i * t;
        }

        public void rotateBack() {
            rotate(-1);
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