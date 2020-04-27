package ui.panels;

import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import app.Game;


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
        setPreferredSize(w, (int) (1.5 * muteButton.getIcon().getIconHeight()));
    }
    
    private JButton createMuteButton() {
        final JButton btn;
        Icon muteIcon = new ImageIcon("resources/icons8-mute-48.png");
        btn = new JButton(muteIcon);
        btn.setBackground(java.awt.Color.RED);
        btn.setFocusable(false);
        btn.addActionListener((
            ActionEvent e) -> {
                Game.getInstance().toggleMute();
                isMute = !isMute;
                toggleMute(isMute);
            });
        return btn;
    }

    private JButton createPauseButton() {
        final JButton btn;
        btn = new JButton("Pause");
        btn.setFocusable(false);
        btn.addActionListener( 
            (ActionEvent e) -> {
                Game.getInstance().togglePause();
                isPaused = !isPaused;
                toggleButtonText(isPaused);
            });
        return btn;
    }

    private JComponent controlPanelBox(JButton muteButton, JButton pauseButton) {
        Box b = Box.createHorizontalBox();
        b.add(muteButton);
        b.add(pauseButton);
        return b;
    }

    private void toggleButtonText(boolean isPaused) {
        if (!isPaused) {
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