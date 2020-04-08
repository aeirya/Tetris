package ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    int a= 0;
    public GamePanel() {
        setBackground(new Color(30,30,35));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        arch.genBox().draw(g);
    }

}