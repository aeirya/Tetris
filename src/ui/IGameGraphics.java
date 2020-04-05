package ui;

public interface IGameGraphics {

    default void redraw() {
        paint();
    }

    boolean paint();
}