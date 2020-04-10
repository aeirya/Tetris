package ui.panels;

import java.awt.Graphics;

import controllers.LevelDesigner;
import models.TetriminoGenerator;
import models.tetriminos.Tetrimino;

public class GamePanel extends Panel {

    LevelDesigner level;
    Tetrimino t;
    
    public GamePanel(int w, int h) {
        super(w,h);
        setBackground(30,30,35);
        level = new LevelDesigner();
        t = TetriminoGenerator.random(1, 1);
    }

    public void draw(Graphics g) {
        level.draw(g);
        t.fall();
        // t.moveRight();
        t.draw(g);
	}
}