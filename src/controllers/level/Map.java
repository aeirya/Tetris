package controllers.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import models.interfaces.Animate;
import models.interfaces.Drawable;
import models.interfaces.IGameObject;
import models.tetrimino.AnimationType;
import models.tetrimino.Animator;
import models.Architect.Box;

public class Map implements Drawable, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Box[][] list;

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

    public int checkLines() {
        SangeSabur thePatientWiseMan = new SangeSabur();
        int counter = 0;
        List<Integer> clearList = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            Line line = getLine(i);
            if (line.isFull()) {
                util.log.GameLogger.outdatedLog("line full!");
                counter += 1;
                thePatientWiseMan.increasePatience();
                clearList.add(i);
                new Animator().setAnimation(AnimationType.BLINK).setOnDone(() -> {
                    synchronized(thePatientWiseMan) {
                        thePatientWiseMan.report();
                        thePatientWiseMan.notifyAll();
                    }
                }).playAnimation(line);
            }
        }
        thePatientWiseMan.waitFor(() -> cleanup(clearList));
        return counter;
    }

    private void cleanup(List<Integer> clearList) {
        for (Integer i : clearList) {
            getLine(i).clear();
            dropLevel(i);
        }
        //because of graphical glitches:
        clearList.forEach((Integer i) -> {
            getLine(i).show();
            if (!clearList.contains(i-1)) getLine(i-1).show();
        });
    }

    private class SangeSabur {

        private int docs = 0;
        private int checked = 0;
        public void increasePatience() { docs++; }
        public void report() { checked++; }
        public boolean isFinished() { return docs == checked; }
        public void waitFor(Runnable run) {
            new Thread(
                () -> {
                    synchronized (this) {
                        try {
                            while(!this.isFinished()) {
                                this.wait();
                            }
                            run.run();
                            
                        } catch (InterruptedException e) {
                            util.log.GameLogger.interrupted();
                            Thread.currentThread().interrupt();
                        }
                    }
                }).start();
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

    private class Line implements Animate {

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

	public void draw(Graphics g) {
        for (int i=0; i<getWidth(); i++) {
            for (int j=0; j<getHeight(); j++) {
                Box box = list[j][i];
                if (box != null) box.draw(g);
            }
        }
	}
}