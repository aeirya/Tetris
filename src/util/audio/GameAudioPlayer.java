package util.audio;

public class GameAudioPlayer implements IGameAudioPlayer {

    private final SimpleAudioPlayer player = initiatePlayer();
    private boolean isMute = false;
    private boolean isPlaying = true;

    public GameAudioPlayer() {
        SoundEffect.init();
        player.play();
    }

    private SimpleAudioPlayer initiatePlayer() {
        try {
            return new SimpleAudioPlayer();
        } catch (Exception ex) {
            util.log.GameLogger.info("returning null player");
            audioError(ex);
            return null;
        }
    }

    public void togglePlay() {
        isPlaying = !isPlaying;
        if (isPlaying && !isMute) play();
        else pause();
    }
    
    public void toggleMute() {
        isMute = !isMute;
        togglePlay();
    }

    public void play() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public boolean isMute() {
        return player.getStatus().equals("paused");
    }

    private void audioError(Exception ex) {
        util.log.GameLogger.error(ex, this);
    }
}