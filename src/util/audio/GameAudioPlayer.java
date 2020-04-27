package util.audio;

public class GameAudioPlayer implements IGameAudioPlayer {

    private final SimpleAudioPlayer player = initiatePlayer();

    public GameAudioPlayer() {
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
    // SoundEffect.init();

    public void togglePlay() {
        if (player.getStatus().equals("play")) pause();
        else play();
    }

    public void play() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    private void audioError(Exception ex) {
        util.log.GameLogger.error(ex, this);
    }
}