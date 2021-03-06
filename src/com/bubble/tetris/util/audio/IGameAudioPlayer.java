package com.bubble.tetris.util.audio;

public interface IGameAudioPlayer {

    void play();
    void pause();
    void togglePlay();
    void toggleMute();
    boolean isMute();
    void reset();
}