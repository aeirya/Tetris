package controllers.level.map;

import java.util.ArrayList;
import java.util.List;

import controllers.animation.AnimationType;
import controllers.animation.Animator;
import controllers.level.map.Map.Line;

public class MapCleaner implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

    private final Map map;
    private List<Integer> clearList = new ArrayList<>();

    public MapCleaner(Map map) {
        this.map = map;
    }

    public int checkLines() {
        clearList.clear();
        SangeSabur thePatientWiseMan = new SangeSabur();
        int counter = 0;
        for (int i = 0; i < map.getHeight(); i++) {
            Line line = map.getLine(i);
            if (line.isFull()) {
                util.log.GameLogger.outdatedLog("line full!");
                counter += 1;
                clearList.add(i);
                thePatientWiseMan.increasePatience();
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
            map.getLine(i).clear();
            dropLevel(i);
        }
        //because of graphical glitches: (can be removed now)
        // clearList.forEach((Integer i) -> {
        //     map.getLine(i).show();
        //     if (!clearList.contains(i-1)) map.getLine(i-1).show();
        // });
    }

    private void dropLevel(int n) {
        for (int i = n-1; i>=0; i--) {
            Line line = map.getLine(i);
            if (line.isEmpty()) break;
            line.moveDown();
            map.replaceLine(i+1, line);
        }
        Line head = map.getLine(0);
        if (!head.isEmpty()) head.clear();
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
}