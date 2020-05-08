package com.bubble.tetris.ui.panels.sidepanel;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.File;
import com.bubble.tetris.app.Game;
import com.bubble.tetris.models.state.GameState;
import com.bubble.tetris.ui.panels.Panel;
import com.bubble.tetris.util.CustomListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ControlPanel extends Panel {

    private static final String MUTE_ICON_PATH = "resources/icons8-mute-48.png";

    private final JButton pauseButton;
    private final JButton muteButton;

    protected ControlPanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        muteButton = createMuteButton();
        pauseButton = createPauseButton();
        if (muteHasIcon()) {
            controlPanelGridBag(muteButton, pauseButton);
        } else
            alternativeControlPanelBox(muteButton, pauseButton);
        setSize(w);
    }

    private void setSize(int w) {
        final int h = (muteButton.getIcon() == null) ? 50 : (int) (1.5 * muteButton.getIcon().getIconHeight());
        setPreferredSize(w, h);
    }

    private boolean muteHasIcon() {
        return (muteButton.getIcon() != null);
    }

    private boolean muteIconPathExists() {
        return new File(MUTE_ICON_PATH).exists();
    }

    private JButton createMuteButton() {
        final JButton btn;
        if (!muteIconPathExists())
            btn = new JButton("M(u)te");
        else {
            Icon muteIcon = new ImageIcon(MUTE_ICON_PATH);
            btn = new JButton(muteIcon);
        }
        btn.setBackground(java.awt.Color.RED);
        btn.setFocusable(false);
        btn.addActionListener((ActionEvent e) -> 
            Game.getInstance().toggleMute()
        );
        btn.setToolTipText("M(u)te the background sound");
        new CustomListener<ControlPanel> (
            this, 250, 
            c -> c.toggleMute(
                Game.getInstance().isMute()
            )).start();  
        return btn;
    }

    private JButton createPauseButton() {
        final JButton btn;
        btn = new JButton("(P)ause");
        btn.setFocusable(false);
        btn.addActionListener((ActionEvent e) ->
            Game.getInstance().togglePause()
        );
        return btn;
    }

    private JButton createMenuButton() {
        final JButton btn;
        btn = new JButton("(M)enu");
        btn.setFocusable(false);
        btn.addActionListener((ActionEvent e) -> Game.getInstance().toggleMenu());
        return btn;
    }

    private void controlPanelGridBag(JButton muteButton, JButton pauseButton) {
        JButton btnMenu = createMenuButton();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(muteButton, c);
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(pauseButton, c);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(btnMenu, c);
    }

    private void alternativeControlPanelBox(JButton muteButton, JButton pauseButton) {
        com.bubble.tetris.util.log.GameLogger.log("alternative mute button (icon not found)");
        JButton btnMenu = createMenuButton();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(muteButton, c);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(pauseButton, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        pane.add(btnMenu, c);
    }

    private void toggleButtonText(boolean isPaused) {
        if (!isPaused) {
            final String PAUSE = "(P)ause";
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

    @Override
    public void update(GameState state) {
        toggleButtonText(
            Game.getInstance().isPaused()
        );
        toggleMute(
            Game.getInstance().isMute()
        );
    }
}