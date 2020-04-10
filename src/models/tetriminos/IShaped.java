package models.tetriminos;

import models.Shape;

public class IShaped extends Shape {
    
public IShaped() {
        coordinates = ShapeCoordinate.makeList(1,2,1,1,1,-1,1,-2);
    }
}