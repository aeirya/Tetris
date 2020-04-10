package ui.panels;

import java.awt.Graphics;
import models.TetriminoGenerator;
import models.tetriminos.Tetrimino;

public class GamePanel extends Panel {

    Tetrimino t;

    public GamePanel() {
        setBackground(30,30,35);
        t = TetriminoGenerator.random(2,2);
    }

    public void draw(Graphics g) {
        t.draw(g);
	}
}