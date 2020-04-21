package ui.panels;

import java.awt.Graphics;

import ui.ComponentGenerator;
import ui.drawlist.DrawList;

public class NextPanel extends Panel {

    private ComponentGenerator gen;
    private DrawList drawlist = null;

    protected NextPanel(int w, int h) {
        super(w, h);
        setBackground(100,100,100);
        gen = new ComponentGenerator(w, h);
        pane.add(gen.label("Next"));
    }

    @Override
    public void update(DrawList list) {
        drawlist = list;
    }

    @Override 
    public void draw(Graphics g) {
        if (drawlist!=null) drawlist.draw(g);
    }
}