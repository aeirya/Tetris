package models.tetrimino.shapes;

import models.tetrimino.Shape;

public class TShaped extends Shape {

    public TShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,0,1,0,-1,1,0);
    }

}