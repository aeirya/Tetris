package models.tetrimino.shapes;

import models.tetrimino.Shape;

public class JShaped extends Shape {

    private static final long serialVersionUID = -9103777353057172042L;

    public JShaped() {
        coordinates = ShapeCoordinate.makeList(0,1,0,0,0,-1,1,-1);
    }
}