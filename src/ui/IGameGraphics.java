package ui;

import util.time.GameTimer;

public interface IGameGraphics {

    default void redraw() {
        paint();
        onDone();
    }

    void setup();

    void paint();

    default void onDone() {
        GameTimer.getInstance().resume();
    }
}