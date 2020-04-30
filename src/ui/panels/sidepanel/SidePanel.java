package ui.panels.sidepanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import controllers.state.GameState;
import ui.panels.Panel;

public class SidePanel extends Panel {

    private final NextPanel nextPanel;
    private final ScorePanel scorePanel;
    private final ControlPanel controlPanel;
    private GameState lastState = null;
    
    public SidePanel(int w, int h) {
        super(w, h);
        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        nextPanel = new NextPanel(w,h);
        scorePanel = new ScorePanel(w, h);
        controlPanel = new ControlPanel(w, h);
        initiate();
    }
    
    private void initiate() {
        scorePanel.addToPanel(pane);
        nextPanel.addToPanel(pane);
        pane.add(Box.createVerticalGlue());
        controlPanel.addToPanel(pane);
    }
    
    @Override
    public void update(GameState state) {
        if (state != lastState) {
            lastState = state;
            nextPanel.update(state);
            scorePanel.update(state);
            controlPanel.update(state);
        }
    }
}