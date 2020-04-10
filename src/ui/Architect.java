package ui;

import java.awt.Color;
import java.awt.Dimension;
import models.Drawable;
import models.GameObject;
import models.IGameObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

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
        sizes.calculate(size.width, (int) (0.97 * size.height));
    }
    
    public Box genBox(int x, int y) {
        return new Box(x,y); 
    }

    public Box genBox() {
        return genBox(0,0);
    }

    public static class SizeManager {
        
        public static final int NCOLUMNS = 12;
        public static final int NROWS = 21;
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

    public class Box extends GameObject implements Drawable {

        private Color c;

        public Box(int x, int y) {
            super(x,y);
            c = new Color(170,30,30);
        }

        public Box() {
            this(0,0);
        }

        private Box(int x, int y, Color c) {
            super(x, y);
            this.c = c;
        }

        public Box(int r, int g, int b) {
            super(0,0);
            this.c = new Color(r,g,b);
        }
        
        public void draw(Graphics g) {
            int width = (int) sizes.getBoxDim().getWidth();
            int height = (int) sizes.getBoxDim().getHeight();
            int x = this.x*width;
            int y = this.y*height;
            g.setColor(c);
            g.fillRect(x, y, width, height);
            drawBorders(x+2, y+2, width-4, height-4, 2, g);
            drawBorders(x,y,width,height, 2, g);
        }
        
        private void drawBorders(int x, int y, int width, int height, int w, Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(c.darker());
            drawVerticalLine(x+width-w, y, height, w, g2);
            drawHorizonalLine(x, y+height-w, width, w, g2);
            g2.setColor(c.brighter());
            drawHorizonalLine(x, y+w, width, w, g2);
            drawVerticalLine(x+w, y, width, w, g2);
        }
        
        private void drawVerticalLine(int x, int y, int length, int w, Graphics2D g) {
            g.setStroke(new BasicStroke((w*2)));
            g.drawLine(x, y+w, x, y-w+length);
        }
        
        private void drawHorizonalLine(int x, int y, int length, int w, Graphics2D g) {
            g.setStroke(new BasicStroke((w*2)));
            g.drawLine(x+w, y, x+length-w, y);
        }

        @Override
        public IGameObject copy() {
            return new Box(x, y, c);
        }
    }
}