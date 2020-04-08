package ui.panels;

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public abstract class Panel implements IPanel {

    private JPanel pane = new JPanel() {
        private static final long serialVersionUID = 1L;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }
    };

	@Override
	public void addToPanel(JPanel container, Object constraints) {
		container.add(pane, constraints);
    }
    
    protected void setBackground(int r, int g, int b) {
        pane.setBackground(new Color(r,g,b));
    }

    protected void setPreferredSize(int w, int h) {
        pane.setPreferredSize(new Dimension(w,h));
    }
}