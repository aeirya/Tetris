package ui.panels;

import java.awt.Graphics;

import models.tetrimino.Tetrimino;
import ui.ComponentGenerator;

public class NextPanel extends Panel {

    private Tetrimino next = null;
    private ComponentGenerator gen;

    protected NextPanel(int w, int h) {
        super(w, h);
        setBackground(100,100,100);
        gen = new ComponentGenerator(w, h);
        pane.add(gen.label("Next"));
    }

    @Override 
    public void draw(Graphics g) {
        if(next!=null) {
            next.draw(g); System.out.println("drawing");
        }
    }

    public void showTetrimino(Tetrimino tetrimino) {
        util.log.GameLogger.info("sho me de wei");
        next = (Tetrimino)tetrimino.copy();
        pane.repaint();
    }
}