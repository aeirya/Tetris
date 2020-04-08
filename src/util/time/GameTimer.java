package util.time;

public class GameTimer {
    final Timer timer = new Timer();
    final Lock lock = new Lock();
    static final long INTERVAL = 1000 / 1;
    static final GameTimer instance = new GameTimer();

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
}