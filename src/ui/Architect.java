package ui;

import java.awt.Color;
import java.awt.Dimension;
import models.Drawable;

import java.awt.Graphics;
import java.awt.Point;

/** Calculates size of boxes and generates objects */
public class Architect {

    private final SizeManager sizes = new SizeManager();
    
    private static class ArchitictHolder {
        public static final Architect instance = new Architect();
    }

    public static Architect getInstance() {
        return ArchitictHolder.instance;
    }

    public void updateNumbers(Dimension size) {
        sizes.calculate(size.width, size.height);
    }
    
    public Box genBox(int x, int y) {
        return new Box(x,y); 
    }

    class SizeManager {
        
        private static final int NCOLUMNS = 12;
        private static final int NROWS = 21;
        private final Dimension boxDimension = new Dimension();

        public void calculate(int sw, int sh) {
            boxDimension.setSize(calculateBoxSize(sw, sh));
        }

        private Dimension calculateBoxSize(int sw, int sh) {
            int w = sw/NCOLUMNS;
            int h = sh/NROWS;
            return new Dimension(w,h);
        }

        public Dimension getBoxDim() {
            return boxDimension;
        }
    }

    public class Box implements Drawable {

        private int x;
        private int y;
        private int width;
        private int height;

        public Box(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void draw(Graphics g) {
            width = (int) sizes.getBoxDim().getWidth();
            height = (int) sizes.getBoxDim().getHeight();
            g.setColor(new Color(200,30,30));
            g.fillRect(x, y, width, height);
        }
    }
}