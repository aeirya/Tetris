package com.bubble.tetris.util.file.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {

    private static String defaultFilePath;

    private Save() { }

    public static Object load(final String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
            return ois.readObject();
        } catch(IOException|ClassNotFoundException ex) {
            com.bubble.tetris.util.log.GameLogger.error(ex, fileName);
            return null;
        }
    }

    public static void save(final Object obj, final String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
            oos.writeObject(obj);
        } catch(final IOException ex) {
            com.bubble.tetris.util.log.GameLogger.error(ex, Save.class);
        }
    }

    public static Object load() {
        return load(defaultFilePath);
    }
}