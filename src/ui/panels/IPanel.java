package ui.panels;

import javax.swing.JPanel;

import controllers.GameState;
import models.interfaces.Drawable;
import java.awt.Graphics;

public interface IPanel extends Drawable {

    void addToPanel(JPanel container, Object constrainsts);
    default void update(GameState updates) {}
    default void draw(Graphics g) {}
}