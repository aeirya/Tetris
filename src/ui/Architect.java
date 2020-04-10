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
        sizes.calculate(size.width, size.height);
    }
    
    public Box genBox(int x, int y) {
        return new Box(x,y); 
    }

    public Box genBox() {
        return genBox(0,0);
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

    public class Box extends GameObject implements Drawable {

        private Color c;

        public Box(int x, int y) {
            super(x,y);
            c = new Color(170,30,30);
        }

        public Box() {
            this(0,0);
        }
        
        public void draw(Graphics g) {
            int width = (int) sizes.getBoxDim().getWidth();
            int height = (int) sizes.getBoxDim().getHeight();
            int x = this.x*width;
            int y = this.y*height;
            g.setColor(c);
            g.fillRect(x, y, width, height);
            drawBorders(x,y,width,height,g);
        }
        
        private void drawBorders(int x, int y, int width, int height, Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(c.darker());
            g2.drawLine(x, y+height, x+width, y+height);
            g2.drawLine(x+width, y, x+width, y+height);
            g2.setStroke(new BasicStroke(2));
            g2.setColor(c.brighter());
            g2.drawLine(x, y, x+width, y);
            g2.drawLine(x, y, x, y+height);
        }

        @Override
        public IGameObject copy() {
            return new Box(x, y);
        }
    }
}