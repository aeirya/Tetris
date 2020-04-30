package models;

import java.awt.Color;
import java.awt.Dimension;

import models.interfaces.Drawable;
import models.interfaces.IGameObject;
import models.tetrimino.TetriminoGenerator.TColor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

/** Calculates size of boxes and generates objects */
public class Architect implements java.io.Serializable {

    private static final long serialVersionUID = -408508351995967127L;

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

    public void boxesNumber(Dimension boxes) {
        SizeManager.boxNumbers(boxes.width, boxes.height);
    }
    
    public Box genBox(int x, int y) {
        return new Box(x,y); 
    }

    public Box genBox() {
        return genBox(0,0);
    }

    public static class SizeManager implements java.io.Serializable {
        
        private static final long serialVersionUID = 1L;
        
        private static int columns = 12;
        private static int rows = 21;
        private final Dimension boxDimension = new Dimension();

        public static void boxNumbers(int c, int r) {
            columns = c;
            rows = r;
        }

        public void calculate(int sw, int sh) {
            boxDimension.setSize(calculateBoxSize(sw, sh));
        }

        private Dimension calculateBoxSize(int sw, int sh) {
            int w = sw/columns;
            int h = sh/rows;
            return new Dimension(w,h);
        }

        public Dimension getBoxDim() {
            return boxDimension;
        }

        public static int getRows() {
            return rows;
        }

        public static int getColumns() {
            return columns;
        }
    }

    public class Box extends GameObject implements Drawable {

        private static final long serialVersionUID = 1L;
        
        private Color c;
        private boolean isSimple = true;
        private boolean isEmpty = false;

        public Box(int x, int y) {
            super(x,y);
            c = new Color(170,30,30);
        }

        public Box() {
            this(0,0);
            isEmpty = true;
        }

        public Box(int x, int y, Color c, boolean isSimple) {
            super(x, y);
            this.c = c;
            this.isSimple = isSimple;
        }

        public Box(int x, int y, Color c) {
            super(x, y);
            this.c = c;
        }

        public Box(int r, int g, int b) {
            super(0,0);
            this.c = new Color(r,g,b);
        }

        public Box(TColor c, boolean isSimple) {
            this(0,0,TColor.get(c));
            this.isSimple = isSimple;
        }
        
        public boolean isEmpty() {
            return isEmpty;
        }

        private int calcStrokeWidth(int width, int height) {
            int a = 2+height/38;
            int b = 2+width/50;
            return Math.max(a,b);
        }

        public void draw(Graphics g) {
            int width = (int) sizes.getBoxDim().getWidth();
            int height = (int) sizes.getBoxDim().getHeight();
            int x = this.x*width;
            int y = this.y*height;
            g.setColor(c);
            g.fillRect(x, y, width, height);
            int strokeWidth = calcStrokeWidth(width, height);
            drawBorders(x+2, y+2, width-4, height-4, strokeWidth, g);
            drawBorders(x,y,width,height, strokeWidth, g);
            if (!isSimple) {
                drawPokerface(x, y, width, height, g);
            }
        }
        
        private void drawBorders(int x, int y, int width, int height, int w, Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(c.darker());
            drawHorizonalLine(x, y+height-w, width, w, g2);
            drawVerticalLine(x+width-w, y, height, w, g2);
            g2.setColor(c.brighter());
            drawHorizonalLine(x, y+w, width, w, g2);
            drawVerticalLine(x+w, y, height, w, g2);
        }
        
        private void drawVerticalLine(int x, int y, int length, int w, Graphics2D g) {
            g.setStroke(new BasicStroke((w*2)));
            g.drawLine(x, y+w, x, y-w+length);
        }
        
        private void drawHorizonalLine(int x, int y, int length, int w, Graphics2D g) {
            g.setStroke(new BasicStroke((w*2)));
            g.drawLine(x+w, y, x+length-w, y);
        }

        private void drawPokerface(int x, int y, int width, int height, Graphics g) {
            Graphics2D g2 = ((Graphics2D) g);
            g2.setStroke(new BasicStroke(2)); 
            final int x1 = (int)(x+0.35*width);
            final int x2 = (int)(x+(1-0.35)*width);
            final int y1 = (int)(y+0.45*height);
            final int y2 = (int)(y+0.60*height);
            g2.drawOval(x1, y1, 1,1);
            g2.drawOval(x2, y1, 1,1);
            g2.drawLine(x1, y2, x2, y2);
        }

        @Override
        public IGameObject copy() {
            return new Box(x, y, c, isSimple);
        }

        public String toString() {
            return "Box at " +x + ","+y;
        }
    }
}