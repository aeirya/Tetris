package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Coordinate;
import models.Drawable;
import models.TetriminoGenerator;
import models.TetriminoGenerator.TColor;
import models.TetriminoGenerator.TetriminoShape;
import models.tetriminos.Tetrimino;
import ui.Architect;
import ui.Architect.Box;
import java.awt.Graphics;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LevelDesigner implements Drawable {

    private static final int N_COL = Architect.SizeManager.getColumns();
    private static final int N_ROW = Architect.SizeManager.getRows();
    private Architect arch = Architect.getInstance();
    private Random rand;

    private Drawable[] wall;

    public LevelDesigner() {
        rand = random();
        wall = spawnWall();
    }

    public Box[][] create2DBoxList() {
        return new Box[N_ROW][N_COL];
    }

    public Box[] generateBoxes(Box sample, List<Coordinate> cList) {
        Box[] list = new Box[cList.size()];
        int i = 0;
        for (Coordinate c : cList) {
            list[i] = (Box)sample.updatedCoordinates(c);
            i+=1;
        }
        return list;
    }

    public Box[] spawnWall() {
        List<Coordinate> cList = new ArrayList<>();
        Box sample = arch.new Box(TColor.WALL_GREY, false);
        for (int i = 0; i < N_ROW; i++) {
            cList.add(new Coordinate(0,i));
            cList.add(new Coordinate(N_COL-1, i));
        }
        for (int i = 1; i < N_COL-1; i++) {
            cList.add(new Coordinate(i,N_ROW-1));
        }
        return generateBoxes(sample, cList);
    }

    public Box[] spawnBackgroundPixels() {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 1; i < N_COL-1; i++) {
            for (int j=0; j< N_ROW-1; j++) {
                list.add(new Coordinate(i,j));
            }
        }
        
        Box sample = arch.new Box(TColor.BACKGROUND_BLACK, true);
        return generateBoxes(sample, list);
    }

    public Tetrimino spawnTetrimino() {
        int x = N_COL>=5 ? rand.nextInt(N_COL-4)+2 : 2;
        int y = -1;
        util.log.GameLogger.outdatedLog("Spawning at "+x+","+y);
        return TetriminoGenerator.random(x, y);
    }

    public List<Tetrimino> spawnAllMinosTest() {
        List<Tetrimino> result = new ArrayList<>();
        int[] x = { 2, 7 };
        boolean a = false;
        int[] y = { 1, 5, 9, 15, 19, 25 };
        int c = 0;
        for (int i=0; i<8; i++) {
            result.add (
                TetriminoGenerator.create(TetriminoShape.values()[rand.nextInt(TetriminoShape.values().length)],
                a ? x[0] : x[1], y[c] + 1
                , TColor.RANDOM)
            );
            a = !a;
            if(!a) c = ++c % 4;
        }
        return result;
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