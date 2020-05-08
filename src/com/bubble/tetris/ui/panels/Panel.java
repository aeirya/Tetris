package com.bubble.tetris.ui.panels;

import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Container;

public abstract class Panel implements IPanel {

    protected int width;
    protected int height;

    protected JPanel pane = new JPanel() {
        private static final long serialVersionUID = 1L;
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }
    };

    /** @param width
     *  @param height */
    protected Panel(int w, int h) {
        setPreferredSize(w, h);
        this.width = w;
        this.height = h;
    }

    @Deprecated
    public JPanel getPane() {
        return this.pane;
    }

    public void addToPanel(Container container) {
        container.add(pane);
    }

	@Override
	public void addToPanel(Container container, Object constraints) {
        container.add(pane, constraints);
    }

    public void addToPanel(Container container, Point location) {
        container.add(pane);
        pane.setLocation(location);
    }

    /** x and y are numbers between 0 and 1, showing the relative location of the panel */
    // public void add(Panel panel, double x, double y) {
    //     Point p = new Point(
    //         (int) (x*width) , (int) (y*height)
    //     );
    //     panel.addToPanel(pane);
    //     panel.setBounds 
    // }

    protected void setBackground(int r, int g, int b) {
        pane.setBackground(new Color(r,g,b));
    }

    protected void setBackground(Color color) {
        pane.setBackground(color);
    }

    protected void setPreferredSize(int w, int h) {
        pane.setPreferredSize(new Dimension(w,h));
    }

    protected void setLayout(LayoutManager mgr) {
        pane.setLayout(mgr);
    }
}