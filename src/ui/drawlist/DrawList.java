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

    public DrawList add(Drawable item) {
        list.add(item);
        return this;
    }

    public DrawList add(IGameObject item) {
        return add((Drawable) item);
    }

    public DrawList add(List<?> list) {
        for (Object d : list) {
            add((Drawable) d);
        }
        return this;
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