package util.audio;

import java.awt.EventQueue;

public class GameAudioPlayer implements IGameAudioPlayer {

    private final SimpleAudioPlayer player = initiatePlayer();

    public GameAudioPlayer() {
        //
    }

    private SimpleAudioPlayer initiatePlayer() {
        try {
            return new SimpleAudioPlayer();
        } catch(Exception ex) {
            audioError(ex);
            return null;
        }
    }
    
    private void audioError(Exception ex) {
        util.log.GameLogger.warning(ex.toString());
    }

    public void start() {
        player.play();
    }

    @Override
    public void background() {
        //
    }

    private void safeJump(Long t) {
        try {
            player.jump(t);
        } catch(Exception ex) {
            audioError(ex);
        } 
    }

    private void timed(Long start, Long end) {
        EventQueue.invokeLater(
            () -> {
                    safeJump(start);    
                    while(true) {
                        if (player.getCurrentFrame()>end) {
                            break;
                        }
                    }
                }
            );
    }

    private void playClip(Long start, Long end) {
        Long t = player.getCurrentFrame();
        timed(start, end);
        safeJump(t);
    }

    public void fall() {
        playClip(4000L, 5000L);
    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void lose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        player.stop();
    }
}