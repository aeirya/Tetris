package app;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import models.Architect;

public class GameSettings {

    private String pathToSettings;
    private final String[] propertyNames = { "username", "screensize", "theme" , "boxes" };
    private List<String> propertyVariables = new LinkedList<>();
    private Properties properties = new Properties();
    private Dimension screenSize;
    private Dimension boxes;

    public GameSettings( String settingsFilePath ) {
        this.pathToSettings = settingsFilePath;
        this.getReady();
    }

    private void getReady() {
        loadGameSettings();
        applyGameSettings();
    }

    private Dimension readSize(String key) {
        final String[] size = properties.getProperty(key).split("x");
        return new Dimension(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    }

    public Dimension getScreenSize() {
        return this.screenSize;
    }

    public Dimension getBoxes() {
        return this.boxes;
    }

    private void applyGameSettings() {
        screenSize = readSize("screensize");
        boxes = readSize("boxes");
        Architect.getInstance().boxesNumber(boxes);
        //read username, and background color
    }

    private String[] defaultValues() {
        return new String[] { "Player", "700x850", "default", "12x21" };
    }

    private void loadGameSettings() {
        try {
            final InputStream inStream = new FileInputStream(pathToSettings);
            properties.load(inStream);
            for( final String name : propertyNames) {
                propertyVariables.add(properties.getProperty(name));
            }
            inStream.close();
        } catch (final FileNotFoundException e) {
            final String[] s = defaultValues();
            propertyVariables = Arrays.asList(s);
            saveGameSettings();
        } catch (final IOException e) {
            ioError();
        }
    }

    private void saveGameSettings() {
        for (int i = 0; i < propertyNames.length; i++) {
            properties.setProperty(propertyNames[i], propertyVariables.get(i));
        }
        final File file = new File(pathToSettings);
        if (!file.exists()) {
            try {
                final boolean result = file.createNewFile();
                if (!result)
                    util.log.GameLogger.log("Can't Create Settings File");
            } catch (final IOException e) {
                ioError();
            }
        }
        try {
            final OutputStream out = new FileOutputStream(pathToSettings);
            properties.store(out, "");
            out.close();
        } catch (final FileNotFoundException e) {
            util.log.GameLogger.log("settings file address not found for saving");
        } catch (final IOException e) {
            ioError();
        }
    }

    private void ioError() {
        util.log.GameLogger.log("IO Exception");
    }
}