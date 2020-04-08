package ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ui.Architect;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(new Color(30,30,35));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Architect.getInstance().genBox().draw(g);
    }

}