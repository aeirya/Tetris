package controllers.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Coordinate;
import models.interfaces.Drawable;
import models.tetrimino.TetriminoGenerator;
import models.tetrimino.TetriminoGenerator.TColor;
import models.tetrimino.TetriminoGenerator.TetriminoShape;
import models.tetrimino.Tetrimino;
import models.Architect;
import models.Architect.Box;
import java.awt.Graphics;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LevelDesigner implements Drawable {

    private static int n_col;
    private static int n_row;
    private Architect arch = Architect.getInstance();
    private Random rand;

    private Drawable[] wall;

    public LevelDesigner() {
        n_col = Architect.SizeManager.getColumns();
        n_row = Architect.SizeManager.getRows();
        rand = random();
        wall = spawnWall();
    }

    public Box[][] create2DBoxList() {
        return new Box[n_row-1][n_col-2];
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
        for (int i = 0; i < n_row; i++) {
            cList.add(new Coordinate(0,i));
            cList.add(new Coordinate(n_col-1, i));
        }
        for (int i = 1; i < n_col-1; i++) {
            cList.add(new Coordinate(i,n_row-1));
        }
        return generateBoxes(sample, cList);
    }

    public Box[] spawnBackgroundPixels() {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 1; i < n_col-1; i++) {
            for (int j=0; j< n_row-1; j++) {
                list.add(new Coordinate(i,j));
            }
        }
        
        Box sample = arch.new Box(TColor.BACKGROUND_BLACK, true);
        return generateBoxes(sample, list);
    }

    public Tetrimino spawnTetrimino(Tetrimino tetrimino) {
        int x = n_col>=5 ? rand.nextInt(n_col-4)+2 : 2;
        int y = -1;
        util.log.GameLogger.outdatedLog("Spawning at "+x+","+y);
        tetrimino = ((Tetrimino) tetrimino.copy());
        tetrimino.move(-1, -2); //becuase of : generate tetrimino
        tetrimino.move(x, y);
        return tetrimino;
    }

    public Tetrimino generateTetrimino() {
        return TetriminoGenerator.random(1, 2);
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