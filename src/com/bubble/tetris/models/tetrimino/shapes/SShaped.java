package com.bubble.tetris.models.tetrimino.shapes;

import com.bubble.tetris.models.tetrimino.Shape;

public class SShaped extends Shape {

    /**
     *
     */
    private static final long serialVersionUID = 2034183224206977810L;

    public SShaped() {
        coordinates = ShapeCoordinate.makeList(0,1,0,0,1,0,1,-1);
    }
}