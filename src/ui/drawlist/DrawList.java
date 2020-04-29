package ui.drawlist;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.interfaces.Drawable;
import models.tetrimino.Tetrimino;

public class DrawList implements Drawable {

    private List<Drawable> list;

    public DrawList() {
        list = new ArrayList<>();
    }

    public DrawList add(Tetrimino next) {
        list.add(next);
        return this;
    }

    public DrawList add(List<?> list) {
        list.stream().forEach(
            (Object obj) -> add((Drawable) obj)
        );
        return this;
    }

    public DrawList add(Drawable...items) {
        list.addAll( Arrays.asList(items) );
        return this;
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public void draw(Graphics g) {
        //creating a copy of the array first, skipping the concurrent modification exception
        for (Object obj : list.toArray()) { 
            if (obj != null) {
                ((Drawable)obj).draw(g);
            }
        }
        //DEBUG NOTE: places obj is empty: end_round
    }
}