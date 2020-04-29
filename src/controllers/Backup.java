package controllers;

import util.file.GameSave;

public class Backup {

    private static final String FILE = "backup.ser";
    private GameState pop;
    public Backup(GameState state) {
        hold(state);
    }
    
    public void hold(GameState state) {
        pop();
        push(state);
    }
    
    private void pop() {
        pop = GameSave.loadState(FILE);
    }

    private void push(GameState state) {
        GameSave.saveState(state, FILE);
    }

    public GameState restore() {
        return pop;
    }
}