package ui.panels;

import java.awt.Graphics;
import models.TetriminoGenerator;
import models.tetriminos.Tetrimino;

public class GamePanel extends Panel {

    Tetrimino t;
    
    public GamePanel(int w, int h) {
        super(w,h);
        setBackground(30,30,35);
        t = TetriminoGenerator.random(1, 1);
    }

    public void draw(Graphics g) {
        t.fall();
        // t.moveRight();
        t.draw(g);
	}
}