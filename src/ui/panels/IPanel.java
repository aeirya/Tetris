package ui.panels;

import controllers.state.GameState;
import models.interfaces.Drawable;
import java.awt.Graphics;
import java.awt.Container;

public interface IPanel extends Drawable {

    void addToPanel(Container container, Object constrainsts);
    default void update(GameState updates) {}
    default void draw(Graphics g) {}
}