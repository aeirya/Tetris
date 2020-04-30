package ui.panels;

import java.awt.Graphics;

import controllers.state.GameState;
import ui.drawlist.DrawList;

public class GamePanel extends Panel {

    private DrawList list = new DrawList();
    
    public GamePanel(int w, int h) {
        super(w,h);
        setBackground(30,30,35);
    }

    @Override
    public void draw(Graphics g) {
        list.draw(g);
    }
    
    @Override
    public void update(GameState state) {
        this.list = (DrawList) state.get(this);
    }
}