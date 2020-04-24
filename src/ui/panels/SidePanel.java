package ui.panels;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import controllers.GameScore;
import controllers.GameState;
import ui.ComponentGenerator;

public class SidePanel extends Panel {

    private final NextPanel nextPanel;
    private final ScorePanel scorePanel;
    private GameScore score = new GameScore();
    private GameState lastState = null;

    public SidePanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        nextPanel = new NextPanel((int)(width*0.75), (int)(height*0.4));
        scorePanel = new ScorePanel(w, h);
        initiate();
    }

    private void initiate() {
        scorePanel.addToPanel(pane);
        nextPanel.addToPanel(pane);
        pane.add(Box.createVerticalGlue());
    }

    @Override
    public void update(GameState state) {
        if (state != lastState) {
            lastState = state;
            nextPanel.update(state);
            scorePanel.update(state);
        }
    }

}