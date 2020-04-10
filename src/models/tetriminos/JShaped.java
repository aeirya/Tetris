package models.tetriminos;

import models.Shape;

public class JShaped extends Shape {

    public JShaped() {
        coordinates = ShapeCoordinate.makeList(-1,0,0,0,1,0,1,-1);
    }
}