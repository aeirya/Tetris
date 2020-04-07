package ui;

import app.GameSettings;
import java.awt.Dimension;
import util.time.GameTimer;

public interface IGameGraphics {

    default void redraw() {
        paint();
        refresh();
        onDone();
    }

    default void setup(GameSettings settings) {
        Dimension screensize = settings.getScreenSize();
        setupFrame(screensize);
    }

    void setupFrame(Dimension size);
    void paint();
    void refresh();

    default void onDone() {
        GameTimer.getInstance().resume();
    }
}