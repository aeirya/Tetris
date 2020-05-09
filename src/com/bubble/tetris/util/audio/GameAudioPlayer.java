package com.bubble.tetris.util.audio;

public class GameAudioPlayer implements IGameAudioPlayer {

    private final SimpleAudioPlayer player = initiatePlayer();
    private boolean isMute = false;

    public GameAudioPlayer() {
        SoundEffect.init();
    }

    private SimpleAudioPlayer initiatePlayer() {
        final String DEFAULT_FILE_PATH = "funny_clip_1.wav";
        try {
            return new SimpleAudioPlayer(DEFAULT_FILE_PATH);
        } catch (Exception ex) {
            com.bubble.tetris.util.log.GameLogger.info("returning null player");
            audioError(ex);
            return null;
        }
    }

    public void togglePlay() {
        if (!player.isPlaying()) {
            if (!isMute) play();
        } else pause();
    }

    public void toggleMute() {
        isMute = !isMute;
        togglePlay();
    }

    public void play() { player.play(); }

    public void pause() { player.pause(); }

    public void reset() { 
        SoundEffect.GAMEOVER.stop(); 
        player.reset(); 
        if (!isMute) player.play();
    }

    public boolean isMute() { return isMute; }

    private void audioError(Exception ex) {
        com.bubble.tetris.util.log.GameLogger.error(ex, this);
    }
}