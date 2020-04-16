package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.Graphics;

public class DrawList implements Drawable {

    private List<Drawable> list;
    private List<IGameObject> objects;

    public DrawList() {
        list = new ArrayList<>();
        objects = new ArrayList<>();
        // this is so unneeded for drawlists rather than davinci =__= maybe should extend it
    }

    public DrawList(List<Drawable> list) {
        this.list = list;
    }

    public void add(Drawable item) {
        list.add(item);
    }

    public void add(IGameObject item) {
        list.add((Drawable) item);
        objects.add(item);
    }

    public void add(Drawable[] list) {
        for (Drawable d : list) {
            add(d);
        }
    }

    public void add(List<?> list) {
        for (Object d : list) {
            add((Drawable) d);
        }
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public void removeAll(Collection<?> c) {
        list.removeAll(c);
    }

    public boolean collides(IGameObject[][] pixels) {
        for (IGameObject go : objects) {
            if (go.collides(pixels)) return true;
        }
        return false;
    }

    public void addTo(IGameObject[][] map) {
        for (IGameObject go : objects) {
            go.addTo(map);
        }
    }

    public void draw(Graphics g) {
        //creating a copy of the array first, ditching the concurrent modification exception
        for (Object obj : list.toArray()) { 
            ((Drawable)obj).draw(g);
        }
    }
}