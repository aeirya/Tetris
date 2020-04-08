package ui.panels;

import java.awt.Graphics;
import javax.swing.JPanel;

import models.Drawable;

public interface IPanel extends Drawable {

    void draw(Graphics g);
    void addToPanel(JPanel container, Object constrainsts);
}