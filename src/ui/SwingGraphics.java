package ui;

public class SwingGraphics implements IGameGraphics {

    @Override
    public void paint() {
        // util.log.GameLogger.log("Painting");
    }

    private void doSomeProcess() {
        int j=0;
        for (int i=0; i <100; i++) {
            j*=2;
        }
    }
}