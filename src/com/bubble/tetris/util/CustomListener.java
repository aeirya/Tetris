package com.bubble.tetris.util;

import java.util.function.Consumer;

public class CustomListener<T> {

    private final T comp;
    protected final Consumer<T> func;
    private final int waitTime;
    
    public CustomListener(T comp, int waitTime, Consumer<T> func) {
        this.comp = comp;
        this.func = func;
        this.waitTime = waitTime;
    }

    public void start() {
        new Thread(this::update).start();
    }

    private synchronized void update() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                func.accept(comp);
                wait(waitTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}