package ui.drawlist;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import models.interfaces.Drawable;
import models.interfaces.IGameObject;

public class DrawList implements Drawable {

    private List<Drawable> list;

    public DrawList() {
        list = new ArrayList<>();
    }

    public void add(Drawable item) {
        list.add(item);
    }

    public void add(IGameObject item) {
        add((Drawable) item);
    }

    public void add(List<?> list) {
        for (Object d : list) {
            add((Drawable) d);
        }
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public void draw(Graphics g) {
        //creating a copy of the array first, ditching the concurrent modification exception
        for (Object obj : list.toArray()) { 
            ((Drawable)obj).draw(g);
        }
    }
}