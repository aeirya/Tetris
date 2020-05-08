package com.bubble.tetris.ui.panels;

import com.bubble.tetris.models.state.GameState;
import com.bubble.tetris.models.interfaces.Drawable;
import java.awt.Graphics;
import java.awt.Container;

public interface IPanel extends Drawable {

    void addToPanel(Container container, Object constrainsts);
    default void update(GameState updates) {}
    default void draw(Graphics g) {}
}