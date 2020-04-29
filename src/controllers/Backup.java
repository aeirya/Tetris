package controllers;

public class Backup {

    private GameState inQueue;
    private GameState currentSave;

    public Backup(GameState state) {
        currentSave = state;
        inQueue = state;
    }
    
    public void hold(GameState state) {
        push();
        queue(state);
    }
    
    private void queue(GameState state) {
        inQueue = state.copy();
    }

    private GameState push() {
        GameState pop = currentSave;
        currentSave = inQueue;
        return pop;
    }

    public GameState restore() {
        GameState pop = push();
        if (pop == null) util.log.GameLogger.log("pushing harder :))");
        return (pop != null) ? pop : push();
    }

    
}