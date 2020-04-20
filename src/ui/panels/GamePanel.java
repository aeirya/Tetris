package ui.panels;

import java.awt.Graphics;
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
    public void update(DrawList list) {
        this.list = list;
    }
}