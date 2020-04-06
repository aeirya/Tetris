package util.time;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import java.util.ArrayList;

public class TaskManager {

    private final List<Runnable> tasks = new ArrayList<>();
    private boolean isWorkerIdle = true;
    private long queueTime = 0;

    private void queue(Runnable function, boolean silent) {
        addTask(function);
        if (!silent) wake();      
    }

    public void queue(Runnable function) {
        queue(function,false);
    }

    private void addTask(Runnable function) {
        tasks.add(function);
    }

    public long getRunningTime() {
        long q = queueTime;
        queueTime = 0;
        return q;
    }

    private void wake() {
        if (isWorkerIdle && !tasks.isEmpty()) {
            executeTasks();
        }
    }

    private void executeTasks() {
        final SwingWorker<Long, Runnable> worker = new TaskManagerWorker(tasks, this::tmwOnDone);
        worker.execute();

        try {
            queueTime += (long) worker.get();
        } catch (InterruptedException | ExecutionException e) {
            util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    private void tmwOnDone() {
        tasks.clear();
        isWorkerIdle = true;
        wake();
    }
    
}