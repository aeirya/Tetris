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
import util.time.Timer;

public class GameSettings {
    final String[] propertyNames = { "user name", "screen size", "background color" };
    List<String> propertyVariables = new LinkedList<>();
    Properties properties = new Properties();
    static final String PATH_TO_SETTINGS = "game.settings";

    public GameSettings() {
        new Timer().meteredStart(this::loadGameSettings);
    }

    private void loadGameSettings() {
        try {
            final InputStream inStream = new FileInputStream(PATH_TO_SETTINGS);
            properties.load(inStream);
            for( final String name : propertyNames) {
                propertyVariables.add(properties.getProperty(name));
            }
            inStream.close();
        } catch (final FileNotFoundException e) {
            final String[] s = { "Player", "600x800", "20:20:20" };
            propertyVariables = Arrays.asList(s);
            saveGameSettings();
        } catch (final IOException e) {
            util.log.GameLogger.log("IOException");
        }
    }

    public Dimension getScreenSize() {
        final String[] size = properties.getProperty("screen size").split("x");
        return new Dimension(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
    }

    private void saveGameSettings() {
        for (int i = 0; i < propertyNames.length; i++) {
            properties.setProperty(propertyNames[i], propertyVariables.get(i));
        }
        final File file = new File(PATH_TO_SETTINGS);
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
            final OutputStream out = new FileOutputStream(PATH_TO_SETTINGS);
            properties.store(out, "");
            out.close();
        } catch (final FileNotFoundException e) {
            //
        } catch (final IOException e) {
            ioError();
        }
    }

    private void ioError() {
        util.log.GameLogger.log("IO Exception");
    }
}