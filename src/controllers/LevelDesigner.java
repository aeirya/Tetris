package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Coordinate;
import models.Drawable;
import models.TetriminoGenerator;
import models.tetriminos.Tetrimino;
import ui.Architect;
import ui.Architect.Box;
import java.awt.Graphics;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LevelDesigner implements Drawable {

    private static final int N_COL = Architect.SizeManager.NCOLUMNS;
    private static final int N_ROW = Architect.SizeManager.NROWS;
    private Architect arch = Architect.getInstance();
    private Random rand;

    private Drawable[] wall;

    public LevelDesigner() {
        rand = random();
        wall = spawnWall();
    }

    public Box[] spawnWall() {
        List<Coordinate> cList = new ArrayList<>();
        Box sample = arch.new Box(100, 100, 100);
        for (int i = 0; i < N_ROW; i++) {
            cList.add(new Coordinate(0,i));
            cList.add(new Coordinate(N_COL-1, i));
        }
        for (int i = 1; i < N_COL-1; i++) {
            cList.add(new Coordinate(i,N_ROW-1));
        }
        Box[] list = new Box[cList.size()];
        int i = 0;
        for (Coordinate c : cList) {
            list[i] = (Box)sample.updatedCoordinates(c);
            i+=1;
        }
        return list;
    }

    public Tetrimino spawnTetrimino() {
        int x = rand.nextInt(N_COL-3)+1;
        int y = -1;
        return TetriminoGenerator.random(x, y);
    }

    public void draw(Graphics g) {
        for (Drawable d : wall) {
            d.draw(g);
        }
    }

    private Random random() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            return new Random();
        }
    }
}