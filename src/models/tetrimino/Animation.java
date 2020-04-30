package models.tetrimino;

import models.interfaces.Animate;

public abstract class Animation {
    
    protected long waitTime;

    public abstract void play(Animate t);
    public abstract void reset();

    public boolean isDone() { 
        return false; 
    }

    public void doWait() throws InterruptedException {
        Thread.sleep(waitTime);
    }
    

    public static class Blink extends Animation {
        
        private static final int REPEATS = 4;
        private int i = 0;

        public Blink() {
            this.waitTime = 250L;
        }

        public void play(Animate t) {
            t.toggleHidden();
            i += 1;
            System.out.println("played");
        }  
        
        @Override
        public boolean isDone() {
            return i >= REPEATS;
        }

        public void reset() {
            i = 0;
        }
    }
}

