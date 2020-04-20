package ui.panels;

import javax.swing.JPanel;
import ui.drawlist.DrawList;
import models.interfaces.Drawable;
import java.awt.Graphics;

public interface IPanel extends Drawable {

    void addToPanel(JPanel container, Object constrainsts);
    default void update(DrawList list) {}
    default void draw(Graphics g) {}
}