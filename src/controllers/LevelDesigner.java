package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Coordinate;
import models.Drawable;
import models.IGameObject;
import ui.Architect;
import ui.Architect.Box;
import java.awt.Graphics;

public class LevelDesigner implements Drawable {

    private static final int N_COL = Architect.SizeManager.NCOLUMNS;
    private static final int N_ROW = Architect.SizeManager.NROWS;
    private Architect arch = Architect.getInstance();
    
    private Drawable[] wall;

    public LevelDesigner() {
        wall = spawnWall();
    }

    public Box[] spawnWall() {
        List<Coordinate> c_list = new ArrayList<>();
        
        // Box sample = new Box(200,200,200);
        Box sample = arch.genBox();
        for (int i = 0; i < N_ROW; i++) {
            c_list.add(new Coordinate(0,i));
            c_list.add(new Coordinate(N_COL-1, i));
        }
        for (int i = 1; i < N_COL-1; i++) {
            c_list.add(new Coordinate(i,N_ROW-1));
        }
        Box[] list = new Box[c_list.size()];
        int i = 0;
        for (Coordinate c : c_list) {
            list[i] = (Box)sample.updatedCoordinates(c);
            i+=1;
        }
        return list;
    }

    public void draw(Graphics g) {
        for (Drawable d : wall) {
            d.draw(g);
        }
    }
}