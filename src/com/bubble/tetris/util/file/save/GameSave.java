package com.bubble.tetris.util.file.save;

import com.bubble.tetris.models.state.GameState;

public class GameSave {

    private GameSave() { }

    private static final String DEFAULT_FILE_PATH = "savedGameState.ser";
    private static String fileName = DEFAULT_FILE_PATH;

    public static GameState loadState(final String fileName) {
        final Object obj = Save.load(fileName);
        return (GameState) obj;
    }

    public static GameState loadState() {
        return loadState(fileName);
    }

    public static void saveState(final GameState state, final String fileName) {
        Save.save(state, fileName);
    }

    public static void saveState(final GameState state) {
        saveState(state, fileName);
    } 
}