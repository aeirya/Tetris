package models.tetrimino;

import models.interfaces.Animate;

public class Animator {

    private Animation animation = null;
    private boolean isPlayAnimation = false;

    private Runnable onDone = () -> {};
    
    public Animator() {
        //
    }

    public Animator(Animation animation, Runnable onDone) {
        this.animation = animation;
        this.onDone = onDone;
    }

    public Animator setAnimation(Animation animation) {
        util.log.GameLogger.outdatedLog("Setting the animation");
        this.animation = animation;
        return this;
    }

    public Animator setOnDone(Runnable onDone) {
        this.onDone = onDone;
        return this;
    }

    public synchronized void playAnimation(Animate t) {
        if (animation != null) {
            util.log.GameLogger.log("Invoked playing animation");
            new Thread(
                () -> {
                isPlayAnimation = true;
                while (isPlayAnimation) {
                    animation.play(t);
                    if (animation.isDone()) {
                        stopAnimation();
                        onDone();
                    }
                    try {
                        animation.doWait();
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
        animation.reset();
    }

    public void onDone() {
        util.log.GameLogger.log("toggle hidden");
        onDone.run();
    }
}