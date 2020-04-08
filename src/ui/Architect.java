package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Graphics;

/** Calculates size of boxes and generates objects */
public class Architect {
    
    final SizeManager mgr = new SizeManager();

    public Architect(Dimension size) {
        updateNumbers(size);
    }  
    
    private void updateNumbers(Dimension size) {
        mgr.calculate(size.width, size.height);
    }
    
    public Box genBox() {
        return new Box(); 
    }

    class SizeManager {
        
        static final int NCOLUMNS = 12;
        static final int NROWS = 21;
        final Dimension boxDimension = new Dimension();

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

    class Box extends JPanel {

        private static final long serialVersionUID = -8277876715945647393L;

        public Box() {
            this.setBackground(new Color(200,0,0));
            this.setPreferredSize(mgr.boxDimension);
       }

        @Override
        protected void printComponent(Graphics g) {
            super.printComponent(g);
        
        }
    }
}