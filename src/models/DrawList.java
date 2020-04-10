package models;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class DrawList implements Drawable {

    List<Drawable> list;

    public DrawList() {
        list = new ArrayList<>();
    }

    public void add(Drawable item) {
        list.add(item);
    }

    public void draw(Graphics g) {
        for (Drawable d : list) {
            d.draw(g);
        }
    }

}