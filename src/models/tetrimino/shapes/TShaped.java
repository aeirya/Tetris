package models.tetrimino.shapes;

import models.tetrimino.Shape;

public class TShaped extends Shape {

    private static final long serialVersionUID = -2476564806973775845L;

    public TShaped() {
        coordinates = ShapeCoordinate.makeList(0,-1,1,0,0,1,0,0);
    }

    @Override
    public void rotate(int i) {
        super.rotate(-1 * i);
    }
}