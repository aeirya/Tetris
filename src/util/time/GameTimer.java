package util.time;

public class GameTimer {
    final Timer timer = new Timer();
    final Timer tickTimer = new Timer();
    final Lock lock = new Lock();
    static final int FPS = 60;
    static final long INTERVAL = 1000/FPS;
    static final float GAME_SPEED = 3.0f;
    static final GameTimer instance = new GameTimer();
    private int frameCounter = 0;
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

    private long calcWaitTime() {
        long l = INTERVAL - timer.tm.getRunningTime();
        return l > 0 ? l : 0;
    }

    public void holdOn() {
        try {
            lock.lock();
            Thread.sleep(calcWaitTime());
        } catch (InterruptedException e) {
            util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    public void resume() {
        lock.unlock();
    }

    public boolean isTickTime() {
        util.log.GameLogger.outdatedLog("fame counter: "+frameCounter);
        util.log.GameLogger.outdatedLog(String.valueOf(waitTime));
        waitTime-= tickTimer.delta();
        if (waitTime<=0) {
            frameCounter=0;
            waitTime = (int) (1000 / GAME_SPEED);
            util.log.GameLogger.log("\u001B[34m"+"tick"+"\u001B[0m");
            return true;
        }
        frameCounter++;
        return false;
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
}