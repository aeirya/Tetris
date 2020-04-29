package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controllers.GameState;

public class GameSave {

    private static final String DEFAULT_FILE_PATH = "savedGameState.ser";
    private static String fileName = DEFAULT_FILE_PATH;

    private GameSave() { }

    public static GameState loadState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            return (GameState)ois.readObject();
        } catch(IOException|ClassNotFoundException ex) {
            util.log.GameLogger.error(ex, GameSave.class);
            return null;
        }
    }

    public static GameState loadState() {
        return loadState(fileName);
    }

    public static void saveState (GameState state, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(state);
        } catch(IOException ex) {
            util.log.GameLogger.error(ex, GameSave.class);
        }
    }

    public static void saveState(GameState state) {
        saveState(state, fileName);
    } 
}