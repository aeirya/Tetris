package ui.panels;

import javax.swing.JPanel;
import models.Drawable;

public interface IPanel extends Drawable {

    void addToPanel(JPanel container, Object constrainsts);
}