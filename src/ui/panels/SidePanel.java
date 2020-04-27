package ui.panels;

import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import app.Game;
import controllers.GameState;
import ui.ComponentGenerator;

public class SidePanel extends Panel {

    private final NextPanel nextPanel;
    private final ScorePanel scorePanel;
    private GameState lastState = null;
    private final JButton pauseButton;
    private final ComponentGenerator c;

    public SidePanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        nextPanel = new NextPanel((int)(width*0.75), (int)(height*0.4));
        scorePanel = new ScorePanel(w, h);
        pauseButton = new JButton("Pause");
        c = new ComponentGenerator(w, h);
        initiate();
    }
    
    private void initiate() {
        scorePanel.addToPanel(pane);
        nextPanel.addToPanel(pane);
        pane.add(Box.createVerticalGlue());
        pane.add(controlPanel());
    }
    
    @Override
    public void update(GameState state) {
        if (state != lastState) {
            lastState = state;
            nextPanel.update(state);
            scorePanel.update(state);
        }
    }
    private JComponent controlPanel() {
        Box b = Box.createHorizontalBox();
        Icon muteIcon = new ImageIcon("resources/icons8-mute-48.png");
        JButton muteButton = new JButton(muteIcon);
        muteButton.setBackground(java.awt.Color.RED);
        muteButton.setFocusable(false);
        muteButton.addActionListener((
            ActionEvent e) -> {
                muteButton.setOpaque(!muteButton.isOpaque());
                Game.getInstance().toggleMute();
            });
        pauseButton.setFocusable(false);
        pauseButton.addActionListener( 
            (ActionEvent e) -> {
                toggleButtonText();
                Game.getInstance().togglePause();
            });
        
        b.add(muteButton);
        b.add(pauseButton);
        return b;
    }

    private void toggleButtonText() {
        final String PAUSE = "Pause";
        final String CONTINUE = "Go on";
        if (pauseButton.getText().equals(PAUSE)) {
            pauseButton.setText(CONTINUE);
        }
        else pauseButton.setText(PAUSE);
    }

}