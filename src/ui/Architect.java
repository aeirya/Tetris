package ui;

import java.awt.Dimension;

/** Calculates size of boxes and generates objects */
public class Architect {
    
    final SizeManager mgr = new SizeManager();

    public Architect(Dimension size) {
        updateNumbers(size);
    }  
    
    private void updateNumbers(Dimension size) {
        mgr.calculate(size.width, size.height);
    }
    
    /** Generate numbers needed */
    public void calculate(int w, int h) {
        // ?
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
}