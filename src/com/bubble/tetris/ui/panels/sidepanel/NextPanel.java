package com.bubble.tetris.ui.panels.sidepanel;

import java.awt.Graphics;
import java.awt.Color;
import com.bubble.tetris.models.state.GameState;
import java.awt.Dimension;
import com.bubble.tetris.models.interfaces.Drawable;
import com.bubble.tetris.ui.ComponentGenerator;
import com.bubble.tetris.ui.panels.Panel;

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
        else com.bubble.tetris.util.log.GameLogger.outdatedLog("next panel is null :|");
    }
}