package models.tetriminos;

import models.Shape;

public class TShaped extends Shape {

    public TShaped() {
        coordinates = Coordinate.makeList(0,0,1,0,-1,0,0,1);
    }

}