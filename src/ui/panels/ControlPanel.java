package ui.panels;

import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.io.File;
import app.Game;


public class ControlPanel extends Panel {

    private static final String MUTE_ICON_PATH = "resources/icons8-mute-48.pngg";

    private final JButton pauseButton;
    private final JButton muteButton;

    private boolean isMute = false;
    private boolean isPaused = false;

    protected ControlPanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        muteButton = createMuteButton();
        pauseButton = createPauseButton();
        if (muteHasIcon()) {
            pane.add(controlPanelBox(muteButton, pauseButton));
        } else pane.add(alternativeControlPanelBox(muteButton, pauseButton));
        setSize(w);
    }

    private void setSize(int w) {
        final int h = (muteButton.getIcon() == null) 
            ? 50 : (int) (1.5 * muteButton.getIcon().getIconHeight());
        setPreferredSize(w,h);
    }
    
    private boolean muteHasIcon() {
        return (muteButton.getIcon() != null);
    }

    private boolean muteIconPathExists() {
        return new File(MUTE_ICON_PATH).exists();
    }

    private JButton createMuteButton() {
        final JButton btn;
        if (!muteIconPathExists()) btn = new JButton("Mute");
        else {
            Icon muteIcon = new ImageIcon(MUTE_ICON_PATH);
            btn = new JButton(muteIcon);
        }
        btn.setPreferredSize(new java.awt.Dimension(20,50));
        btn.setSize(btn.getPreferredSize());
        btn.setBackground(java.awt.Color.RED);
        btn.setFocusable(false);
        btn.addActionListener((
            ActionEvent e) -> {
                Game.getInstance().toggleMute();
                isMute = !isMute;
                toggleMute(isMute);
            });
        btn.setToolTipText("mute the background sound");
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

    private JButton createMenuButton() {
        final JButton btn;
        btn = new JButton("Menu");
        btn.setFocusable(false);
        btn.addActionListener(
            (ActionEvent e) -> Game.getInstance().toggleMenu()
        );
        return btn;
    }

    private JComponent controlPanelBox(JButton muteButton, JButton pauseButton) {
        Box b = Box.createHorizontalBox();
        b.add(muteButton);
        Box v = Box.createVerticalBox();
        v.add(pauseButton);
        v.add(createMenuButton());
        b.add(v);
        return b;
    }

    private JComponent alternativeControlPanelBox(JButton muteButton, JButton pauseButton) {
        Box b = Box.createHorizontalBox();
        b.add(muteButton);
        b.add(pauseButton);
        Box v = Box.createVerticalBox();
        v.add(b);
        v.add(createMenuButton());
        return v;
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