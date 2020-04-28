package ui.panels;

import java.awt.Graphics;

import controllers.GameState;
import models.interfaces.Drawable;
import ui.ComponentGenerator;

public class NextPanel extends Panel {

    private ComponentGenerator gen;
    private Drawable drawlist = null;

    protected NextPanel(int w, int h) {
        super(w, h);
        setBackground(100,100,100);
        gen = new ComponentGenerator(w, h);
        pane.add(gen.label("Next"));
    }

    @Override
    public void update(GameState state) {
        drawlist = (Drawable) state.get(this);
    }

    @Override 
    public void draw(Graphics g) {
        if (drawlist!=null) drawlist.draw(g);
    }
}