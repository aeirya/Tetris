package models.tetrimino.shapes;

import models.tetrimino.Shape;

public class OShaped extends Shape {

    /**
     *
     */
    private static final long serialVersionUID = 5755778419320124150L;

    public OShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,1,0,1,1,0,1);
    }

    @Override
    public void rotate(int i) {
        //do nothing!
    }
}