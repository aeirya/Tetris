package ui.drawlist;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.interfaces.Drawable;

public class DrawList implements Drawable {

    private List<Drawable> list;

    public DrawList() {
        list = new ArrayList<>();
    }

    public DrawList add(Drawable item) {
        list.add(item);
        return this;
    }

    public DrawList add(List<?> list) {
        list.stream().forEach(
            (Object obj) -> add((Drawable) obj)
        );
        return this;
    }

    public DrawList add(Drawable...items) {
        list = Arrays.asList(items);
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