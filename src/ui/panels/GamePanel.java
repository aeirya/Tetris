package ui.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ui.Architect;
import ui.Architect.Box;

public class GamePanel extends Panel {

    private final Architect arch = Architect.getInstance();
    Box box;

    public GamePanel() {
        setBackground(30,30,35);
    }

	@Override
    public void draw(Graphics g) {
        //
	}
}