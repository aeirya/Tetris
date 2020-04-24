package ui.panels;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import controllers.GameScore;
import controllers.GameState;
import ui.ComponentGenerator;

public class ScorePanel extends Panel {

    private final ComponentGenerator c;
    private GameScore score;
    private JComponent myScorePanel;

    /** TODO: panel sizing should be dynamic */
    public ScorePanel(final int w, final int h) {
        super(w, h);
        c = new ComponentGenerator(w, h);
        initiate();
    }

    private void initiate() {
        myScorePanel = scoresPanel();
        pane.add(myScorePanel);
    }

    private JComponent scoresPanel() {
        final Box box = Box.createVerticalBox();
        box.add(c.verticalFiller(0.05, 0.05, 0.1));
        box.add(c.board(score.getScore(), "Score: "));
        box.add(c.verticalFiller(0.05, 0.05, 0.1));
        box.add(c.vSandwich(c.board(1 , "Lines removed")));
        box.add(top10List());
        box.add(c.verticalFiller(0.01, 0.5, 0.5));
        return box;
    }

    private JComponent top10List() {
        final JTextArea textField = new JTextArea();
        final String text = "Top Scores:\n1.10\n2.20";
        textField.setText(text);
        textField.setPreferredSize(
            new Dimension(width, (int) (height * 0.25))
            );
        textField.setFont(textField.getFont().deriveFont((float)16));
        textField.setFocusable(false);
        final JScrollPane pane = new JScrollPane(textField);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return pane;
    }

    @Override
    public void update(GameState state) {
        score = (GameScore) state.get(this);
        
    }

    private JLabel getScoreBoard() {
        JComponent board = myScorePanel.getComponent(1);
        JComponent label = myScorePanel.getComponent(0);
        return (JLabel)label;
    }

    private void changeScore(GameScore score) {
        // score.getScore()
        
    }
}