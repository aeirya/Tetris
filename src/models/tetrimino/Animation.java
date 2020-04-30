package models.tetrimino;

import controllers.input.ICommand;

public abstract class Animation implements ICommand {
    
    public abstract void play(Tetrimino t);
    
    public void act(Tetrimino t) { 
        play(t); 
    }
   
    public boolean isDone() { 
        return false; 
    }

    public abstract void reset();

    public static class Blink extends Animation {
        private static final int REPEATS = 4;
        private int i = 0;
        
        public void play(Tetrimino t) {
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

