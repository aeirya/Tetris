package util.time;

public class Timer {

    private long lastTick;
    
    final TaskManager tm = new TaskManager();

    public Timer() {
        tick();
    }

    /** Runs the function and returns the time cost
     * @deprecated use queue instead
     */
    @Deprecated(forRemoval = false)
    public long meteredStart(Runnable function) {
        tick();
        function.run();
        return delta();
    }

    public void queue(Runnable function) {
        tm.queue(function);
    }

    /** Queue with flush */
    public void autoQueue(Runnable function) {
        queue(function);
        flush();
    }

    public void waitFor(Runnable function) {
        tm.backgroundProcess(function);
    }

    public void flush() {
        tm.flush();
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    /** Updates the time (last tick) */
    private void tick() {
        lastTick = getTime();
    }
    
    /** Returns time difference between currentTime and time */
    private long delta(long time) {
        tick();
        return lastTick - time;
    }

    /** Uses lastTick as the time reference, returns time passed */
    public long delta() {
        return delta(lastTick);
    }
}