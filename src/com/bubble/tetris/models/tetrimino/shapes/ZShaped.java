package com.bubble.tetris.models.tetrimino.shapes;

import com.bubble.tetris.models.tetrimino.Shape;

public class ZShaped extends Shape {

    /**
     *
     */
    private static final long serialVersionUID = -56399583535962854L;

    public ZShaped() {
        coordinates = ShapeCoordinate.makeList(0,0,0,-1,1,0,1,1);
    }
}