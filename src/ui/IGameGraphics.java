package ui;

import app.GameSettings;
import java.awt.Dimension;
import util.time.GameTimer;

public interface IGameGraphics {

    default void redraw() {
        paint();
        onDone();
    }

    default void setup(GameSettings settings) {
        Dimension screensize = settings.getScreenSize();
        setupFrame(screensize);
        setupLayoutManager(screensize);
        start();
    }

    void start();
    void setupLayoutManager(Dimension size);
    void setupFrame(Dimension size);
    void paint();

    default void onDone() {
        GameTimer.getInstance().resume();
    }
}