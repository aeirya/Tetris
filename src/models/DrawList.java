package models;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class DrawList implements Drawable {

    List<Drawable> list;

    public DrawList() {
        list = new ArrayList<>();
    }

    public DrawList(List<Drawable> list) {
        this.list = list;
    }

    public void add(Drawable item) {
        list.add(item);
    }

    public void add(Drawable[] list) {
        for (Drawable d : list) {
            add(d);
        }
    }

    public void add(List<?> list) {
        for (Object d : list) {
            add((Drawable)d);
        }
    }

    public void draw(Graphics g) {
        for (Drawable d : list) {
            d.draw(g);
        }
    }

}