package controllers;

import controllers.input.ICommand;
import models.tetrimino.Tetrimino;

public class Animator {

    private ICommand animation = null;
    private boolean isPlayAnimation = false;
    private static final int INTERVAL = 300;

    public void setAnimation(ICommand animation) {
        util.log.GameLogger.log("Setting the animation");
        this.animation = animation;
    }

    public void playAnimation(Tetrimino t) {
        if (animation != null) {
            util.log.GameLogger.outdatedLog("Invoked playing animation");
            new Thread(
                () -> {
                isPlayAnimation = true;
                while (isPlayAnimation) {
                    animation.act(t);
                    try {
                        Thread.sleep(INTERVAL);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        util.log.GameLogger.interrupted();
                    }
                }
            }).start();
        }
    }

    public void stopAnimation() {
        isPlayAnimation = false;
        animation = null;
    }
}