package util.time;

public class GameTimer {
    final Timer timer = new Timer();
    final Timer tickTimer = new Timer();
    final Lock lock = new Lock();
    static final GameTimer instance = new GameTimer();
    static final int FPS = 30;
    static final long INTERVAL = 1000/FPS;
    static final float GAME_SPEED = 10.0f;
    private long waitTime = 0;

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
        waitTime-= tickTimer.delta();
        if (waitTime <= 0) {
            waitTime = (int) (1000 / GAME_SPEED);
            util.log.GameLogger.log("\u001B[34m"+"tick"+"\u001B[0m");
            return true;
        }
        return false;
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
}