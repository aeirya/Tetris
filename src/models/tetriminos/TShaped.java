package models.tetriminos;

import models.Shape;

public class TShaped extends Shape {

    public TShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,1,0,-1,0,0,1);
    }

}