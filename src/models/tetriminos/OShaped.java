package models.tetriminos;

import models.Shape;

public class OShaped extends Shape {

    public OShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,1,0,1,1,0,1);
    }

    @Override
    public void rotate() {
        //do nothing!
    }
}