package models.tetrimino;

public class Animator {

    private Animation animation = null;
    private boolean isPlayAnimation = false;
    private static final long INTERVAL = 500L;

    public void setAnimation(Animation animation) {
        util.log.GameLogger.outdatedLog("Setting the animation");
        this.animation = animation;
    }

    public synchronized void playAnimation(Tetrimino t) {
        if (animation != null) {
            util.log.GameLogger.log("Invoked playing animation");
            new Thread(
                () -> {
                isPlayAnimation = true;
                while (isPlayAnimation) {
                    animation.play(t);
                    if (animation.isDone()) {
                        stopAnimation();
                    }
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
        animation.reset();
    }
}