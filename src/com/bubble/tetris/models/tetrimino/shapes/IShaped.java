package com.bubble.tetris.models.tetrimino.shapes;

import java.util.ArrayList;
import java.util.List;

import com.bubble.tetris.models.Coordinate;
import com.bubble.tetris.models.interfaces.IGameObject;
import com.bubble.tetris.models.tetrimino.Shape;

public class IShaped extends Shape {
    
    private static final long serialVersionUID = 1L;

    public IShaped() {
        coordinates = ShapeCoordinate.makeList(1,2,1,1,1,-1,1,-2);
    }

    @Override
    public List<IGameObject> applyShape(IGameObject object) {
        List<IGameObject> result = new ArrayList<>();
        for (Coordinate c : check(coordinates)) {

            result.add(object.updatedCoordinates(c));
        }
        return result;
    }

    private Coordinate[] check(ShapeCoordinate[] sc) {
        ShapeCoordinate[] c = sc.clone();
        final int x = c[0].getX();
        if (x == c[1].getX() && (x == -1)) {
            for (int i=0; i<4;i++) {
                c[i] = new ShapeCoordinate(0,c[i].getY());
            }
        }
        final int y = c[0].getY();
        if (y == c[1].getY() && (y == -1)) {
            for (int i=0; i<4;i++) {
                c[i] = new ShapeCoordinate(c[i].getX(), 0);
            }
        }
        for (int i = 0; i < 4; i+=3) {
            if(c[i].getX()==-2) c[i] = new ShapeCoordinate(0, c[i].getY());
            if(c[i].getY()==-2) c[i] = new ShapeCoordinate(c[i].getX(), 0);
        }
        return c;
    }
    
    /*
    Rotations:
    (1,*)==>(*,1)==>(-1,*)==>(*,-1)
    or:
    ()
    */
}