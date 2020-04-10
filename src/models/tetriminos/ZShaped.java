package models.tetriminos;

import models.Shape;

public class ZShaped extends Shape {

    public ZShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,0,-1,1,0,1,1);
    }
}