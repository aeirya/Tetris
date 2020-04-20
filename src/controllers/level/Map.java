package controllers.level;

import java.awt.Graphics;
import java.util.Objects;
import models.interfaces.Drawable;
import models.interfaces.IGameObject;
import models.Architect.Box;

public class Map implements Drawable {

    private Box[][] list;

    public Map(Box[][] boxes) {
        list = boxes;
    }

    public boolean shapeAt(int x, int y) {
        return list[y][x-1] != null;
    }

    public void set(int x, int y, IGameObject gameobject) {
        list[y][x-1] = (Box) gameobject;
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

    public void checkLines() {
        for (int i = 0; i < getHeight(); i++) {
            Line line = getLine(i);
            if (line.isFull()) {
                util.log.GameLogger.outdatedLog("line full!");
                line.clear();
                dropLevel(i);
            }
        }
    }

    private void dropLevel(int n) {
        for (int i = n-1; i>=0; i--) {
            Line line = getLine(i);
            if (line.isEmpty()) break;
            line.moveDown();
            line.replace(list[i+1]);
        }
        Line head = getLine(0);
        if (!head.isEmpty()) head.clear();
    }

    private Line getLine(int i) {
        return new Line(list[i]);
    }

    private class Line {

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
    }
    
    @FunctionalInterface
    interface Condition {
        boolean check(Box box);
    }

	public void draw(Graphics g) {
        for (int i=0; i<getWidth(); i++) {
            for (int j=0; j<getHeight(); j++) {
                Box box = list[j][i];
                if (box != null) box.draw(g);
            }
        }
	}
}