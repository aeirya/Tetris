package com.bubble.tetris.models.tetrimino.shapes;

import com.bubble.tetris.models.tetrimino.Shape;

public class LShaped extends Shape {
    
    /**
    	 *
    	 */
    private static final long serialVersionUID = -4382170874479984027L;

    public LShaped() {
        coordinates = ShapeCoordinate.makeList(0,-1,0,0,0,1,1,1);
    }
}