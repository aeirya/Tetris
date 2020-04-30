package ui.panels;

import java.awt.Graphics;
import java.awt.Color;
import controllers.GameState;
import java.awt.Dimension;
import models.interfaces.Drawable;
import ui.ComponentGenerator;

public class NextPanel extends Panel {

    private ComponentGenerator gen;
    private Drawable drawlist = null;

    protected NextPanel(int w, int h) {
        super(w,h);
        pane.setMinimumSize(
            new Dimension(w, (int) (0.27 * h))
            );
        pane.setMaximumSize(
            new Dimension(w, (int) (0.31 * h))
        );
        pane.setPreferredSize(
            new Dimension(w, (int) (0.29 * h))
            );
        pane.setBackground(new Color(10,10,20).brighter());
        gen = new ComponentGenerator(w, h);
        pane.add(gen.label("NEXT"));
    }

    @Override
    public void update(GameState state) {
        drawlist = (Drawable) state.get(this);
    }

    @Override 
    public void draw(Graphics g) {
        if (drawlist!=null) {
            drawlist.draw(g);
        }
        else util.log.GameLogger.outdatedLog("next panel is null :|");
    }
}