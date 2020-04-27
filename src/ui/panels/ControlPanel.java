package ui.panels;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import app.Game;
import controllers.GameState;

import java.awt.event.ActionEvent;

public class ControlPanel extends Panel {

    private final JButton pauseButton;
    private final JButton muteButton;

    private boolean isMute = false;
    private boolean isPaused = false;

    protected ControlPanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        muteButton = createMuteButton();
        pauseButton = createPauseButton();
        pane.add(controlPanelBox(muteButton, pauseButton));
    }
    
    private JButton createMuteButton() {
        final JButton btn;
        Icon muteIcon = new ImageIcon("resources/icons8-mute-48.png");
        btn = new JButton(muteIcon);
        btn.setBackground(java.awt.Color.RED);
        btn.setFocusable(false);
        btn.addActionListener((
            ActionEvent e) -> 
                Game.getInstance().toggleMute()
            );
        return btn;
    }

    private JButton createPauseButton() {
        final JButton btn;
        btn = new JButton("Pause");
        btn.setFocusable(false);
        btn.addActionListener( 
            (ActionEvent e) -> {
                Game.getInstance().togglePause();
                toggleButtonText();
            });
        return pauseButton;
    }

    private JComponent controlPanelBox(JButton muteButton, JButton pauseButton) {
        Box b = Box.createHorizontalBox();
        b.add(muteButton);
        b.add(pauseButton);
        return b;
    }
    
    @Override
    public void update(GameState state) {
        final Boolean[] received = (Boolean[]) state.get(this);
        final boolean stateIsMute = received[0];
        final boolean stateIsPaused = received[1];
        if (stateIsMute != isMute) {
            isMute = stateIsMute;
            toggleMute(isMute);
        }
        if (stateIsPaused != isPaused) {
            isPaused = stateIsPaused;
            toggleButtonText(isPaused);
        }
    }

    private void toggleButtonText(boolean isPaused) {
        if (isPaused) {
            final String PAUSE = "Pause";
            pauseButton.setText(PAUSE);
        }
        else {
            final String CONTINUE = "Go on";
            pauseButton.setText(CONTINUE);
        }
    }

    private void toggleMute(boolean isMute) {
        muteButton.setOpaque(isMute);
    }
}