package com.bubble.tetris.models.state;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.bubble.tetris.util.file.save.GameSave;

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

    public static void clear() {
        try {
            Files.deleteIfExists(Path.of(FILE));
        } catch (IOException e) {
            com.bubble.tetris.util.log.GameLogger.error(e, Backup.class);
        }
    }
}