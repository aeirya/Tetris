package util.time;

public class GameTimer {
    final Timer timer = new Timer();
    final Lock lock = new Lock();
    static final long INTERVAL = 1000 / 60 * 100;
    static final GameTimer instance = new GameTimer();

    private GameTimer() {
    }

    public static GameTimer getInstance() {
        return instance;
    }

    public void queue(Runnable function) {
        timer.queue(function);
    }

    private long calcWaitTime() {
        // util.log.GameLogger.log(String.valueOf(timer.tm.getRunningTime()));
        long l = INTERVAL - timer.tm.getRunningTime();
        return l > 0 ? l : 0;
    }

    public void holdOn() {
        try {
            lock.lock();
            util.log.GameLogger.log("yaaay i'm free");
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