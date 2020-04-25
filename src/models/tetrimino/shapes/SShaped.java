package models.tetrimino.shapes;

import models.tetrimino.Shape;

public class SShaped extends Shape {

    public SShaped() {
        coordinates = ShapeCoordinate.makeList(0,1,0,0,1,0,1,-1);
    }
}