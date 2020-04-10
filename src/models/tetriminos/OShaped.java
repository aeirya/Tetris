package models.tetriminos;

import models.Shape;

public class OShaped extends Shape {

    public OShaped() {
        coordinates = ShapeCoordinate.makeList(-1,-1,1,1,1,-1,-1,1);
    }
}