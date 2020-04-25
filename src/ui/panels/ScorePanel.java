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
    private final JLabel scoreLabel;
    private final JLabel linesRemovedLabel;
    private GameScore score = new GameScore();

    /** TODO: panel sizing should be dynamic */
    public ScorePanel(final int w, final int h) {
        super(w, h);
        c = new ComponentGenerator(w, h);
        //initiate
        final JComponent myScorePanel = scoresPanel();
        final JComponent scoreBoard = (JComponent) myScorePanel.getComponent(1);
        final JComponent removedLinesBoard = (JComponent)((JComponent) myScorePanel.getComponent(3)).getComponent(0);
        scoreLabel = c.labelOfBoard(scoreBoard);
        linesRemovedLabel = c.labelOfBoard(removedLinesBoard);
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
        changeScore(score);
    }

    private void changeScore(GameScore score) {
        scoreLabel.setText(
            String.valueOf(score.getScore())
        );
        linesRemovedLabel.setText(
            String.valueOf(score.getRemovedLines())
        );
    }
}