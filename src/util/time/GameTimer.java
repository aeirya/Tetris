package util.time;

public class GameTimer {
    private final Timer timer = new Timer();
    private final Timer tickTimer = new Timer();
    private final Lock lock = new Lock();
    private static final GameTimer instance = new GameTimer();
    private static final int FPS = 30;
    private static final long INTERVAL = 1000/FPS;
    private static final float GAME_SPEED = 1.0f;
    private float gameSpeed = GAME_SPEED;
    private long timeStanding = 0;

    private GameTimer() {
    }

    public static GameTimer getInstance() {
        return instance;
    }

    public void queue(Runnable function) {
        timer.queue(function);
    }

    public void flush() {
        timer.flush();
    }

    /** used by the holdOn function. */
    private long calcWaitTime() {
        long l = INTERVAL - timer.tm.getRunningTime();
        return l > 0 ? l : 0;
    }

    public void holdOn() {
        try {
            lock.lock();
            Thread.sleep( calcWaitTime() );
        } catch (InterruptedException e) {
            util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    public void resume() {
        lock.unlock();
    }

    public boolean isTickTime() {
        timeStanding += tickTimer.delta();
        if (timeStanding >= (int) (1000 / gameSpeed)) {
            timeStanding = 0;
                util.log.GameLogger.debug("\u001B[34m"+"tick"+"\u001B[0m");
                return true;
        }
        return false;
    }

    public boolean isLocked() {
        return lock.isLocked();
    }

    public void goFaster() {
        gameSpeed *= 50;
    }

    public void resetSpeed() {
        gameSpeed = GAME_SPEED;
    }
}