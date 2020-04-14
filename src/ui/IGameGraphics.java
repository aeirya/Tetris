package ui;

import app.GameSettings;
import controllers.GameState;
import java.awt.Dimension;
import util.time.GameTimer;
import java.awt.event.KeyListener;

public interface IGameGraphics {

    default void redraw() {
        paint();
        onDone();
    }

    default void setup(GameSettings settings, KeyListener keyListener) {
        Dimension screensize = settings.getScreenSize();
        setupFrame(screensize);
        setupLayoutManager(screensize);
        start();
        addKeyListener(keyListener);
    }

    void start();
    void setupLayoutManager(Dimension size);
    void setupFrame(Dimension size);
    void paint();
    void update(GameState state);
    void addKeyListener(KeyListener l);

    default void onDone() {
        GameTimer.getInstance().resume();
    }
}