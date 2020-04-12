package util.time;

public class GameTimer {
    final Timer timer = new Timer();
    final Timer tickTimer = new Timer();
    final Lock lock = new Lock();
    static final int FPS = 60;
    static final long INTERVAL = 1000/FPS;
    static final float GAME_SPEED = 1.0f;
    static final GameTimer instance = new GameTimer();
    private int frameCounter = 0;

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
            // Thread.sleep(calcWaitTime());
        } catch (InterruptedException e) {
            util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    public void resume() {
        lock.unlock();
    }

    
    public boolean isTickTime() {
        // System.out.println(frameCounter);
        if (GAME_SPEED * frameCounter >= FPS) {
            frameCounter=0;
            util.log.GameLogger.log("tick");
            return true;
        }
        frameCounter++;
        return false;
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
}