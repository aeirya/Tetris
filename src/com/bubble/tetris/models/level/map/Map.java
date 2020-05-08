package com.bubble.tetris.models.level.map;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Objects;

import com.bubble.tetris.models.interfaces.Animate;
import com.bubble.tetris.models.interfaces.Drawable;
import com.bubble.tetris.models.interfaces.IGameObject;
import com.bubble.tetris.models.Architect.Box;

public class Map implements Drawable, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Box[][] list;
    private final MapCleaner cleaner = new MapCleaner(this);

    public Map(Box[][] boxes) {
        list = boxes;
    }

    public boolean shapeAt(int x, int y) {
        return list[y][x - 1] != null;
    }

    public void set(int x, int y, IGameObject gameobject) {
        list[y][x - 1] = (Box) gameobject;
    }

    public int getWidth() {
        return list[0].length;
    }

    public int getHeight() {
        return list.length;
    }

    public void digest(IGameObject go) {
        go.addTo(this);
    }

    public void draw(Graphics g) {
        for (int i=0; i<getWidth(); i++) {
            for (int j=0; j<getHeight(); j++) {
                Box box = list[j][i];
                if (box != null) box.draw(g);
            }
        }
    }

    public void refresh() {
        Arrays.asList(list)
            .forEach(
                (Box[] line) ->
                    Arrays.asList(line)
                        .forEach(
                            (Box box) -> {
                                if(box != null) 
                                    box.show();
                            }
                        )
                );
    } //there is an alternative way for writing this

    public int checkLines() {
        return cleaner.checkLines();
    }

    protected void replaceLine(int i, Line line) {
        line.replace(list[i]);
	}

    protected Line getLine(int i) {
        return new Line(list[i]);
    }

    protected class Line implements Animate {

        private Box[] myLine;
    
        Line(Box[] line) {
            this.myLine = line;
        }
    
        private boolean iterateCondition(Condition cond) {
            for (Box box : myLine) {
                if (!cond.check(box))
                    return false;
            }
            return true;
        }
    
        boolean isEmpty() {
            return this.iterateCondition(Objects::isNull);
        }
    
        boolean isFull() {
            return this.iterateCondition(Objects::nonNull);
        }
    
        void clear() {
            for (int i = 0; i < getWidth(); i++) {
                myLine[i] = null;
            }
        }
    
        void moveDown() {
            for (Box box : myLine) {
                if (box != null)
                    box.fall();
            }
        }
    
        void replace(Box[] dest) {
            System.arraycopy(myLine, 0, dest, 0, getWidth());
            this.clear();
        }
    
        //animate interface
        public void toggleHidden() {
            if (!isEmpty())
                for (Box b : myLine) b.toggleHidden();
        }
    
        public void show() {
            for (Box box : myLine) {
                if (box != null)
                    box.show();
            }
        }
    }
    
    @FunctionalInterface
    interface Condition {
        boolean check(Box box);
    }
}