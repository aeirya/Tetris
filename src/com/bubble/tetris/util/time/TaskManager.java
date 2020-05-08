package com.bubble.tetris.util.time;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import java.util.ArrayList;

public class TaskManager {

    private final List<Runnable> tasks = new ArrayList<>();
    private boolean isWorkerIdle = true;
    private Timer workerTimer;

    public void queue(Runnable function) {
        addTask(function);
    }

    private void addTask(Runnable function) {
        tasks.add(function);
    }

    public void flush() {
        wake();
    }

    public void backgroundProcess(Runnable func) {
        final NormalWorker worker = new NormalWorker(func);
        worker.execute();
    }

    public long getRunningTime() {
        return workerTimer.delta();
    }

    private void wake() {
        if (isWorkerIdle && !tasks.isEmpty()) {
            executeTasks();
        }
    }

    private void executeTasks() {
        final SwingWorker<Timer, Runnable> worker = new QueueWorker(tasks, this::tmwOnDone);
        worker.execute();
        try {
            workerTimer = worker.get();
        } catch (InterruptedException | ExecutionException e) {
            com.bubble.tetris.util.log.GameLogger.interrupted();
            Thread.currentThread().interrupt();
        }
    }

    private void tmwOnDone() {
        tasks.clear();
        isWorkerIdle = true;
        wake();
    }
}