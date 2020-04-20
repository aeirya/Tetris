package ui.panels;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import models.tetrimino.Tetrimino;
import ui.ComponentGenerator;
import ui.drawlist.DrawList;

public class SidePanel extends Panel {

    private NextPanel nextPanel;
    private ComponentGenerator c;

    public SidePanel(int w, int h) {
        super(w, h);
        setBackground(40, 40, 45);
        setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        c = new ComponentGenerator(w, h);
        initiate();
    }

    private void initiate() {
        pane.add(scoresPanel());
        nextPanel = new NextPanel((int)(width*0.75), (int)(height*0.4));
        nextPanel.addToPanel(pane);
        pane.add(Box.createVerticalGlue());
    }

    public void update(DrawList list) {
        nextPanel.update(list);
    }

    private JComponent scoresPanel() {
        Box box = Box.createVerticalBox();
        box.add(c.verticalFiller(0.05, 0.05, 0.1));
        box.add(c.board(1, "Score: "));;
        box.add(c.verticalFiller(0.05, 0.05, 0.1));
        box.add(c.vSandwich(c.board(1 , "Lines removed")));
        box.add(top10List());
        box.add(c.verticalFiller(0.01, 0.5, 0.5));
        return box;
    }

    private JComponent top10List() {
        JTextArea textField = new JTextArea();
        String text = "Top Scores:\n1.10\n2.20";
        textField.setText(text);
        textField.setPreferredSize(
            new Dimension(width, (int) (height * 0.25))
            );
        textField.setFont(textField.getFont().deriveFont((float)20));
        textField.setFocusable(false);
        JScrollPane pane = new JScrollPane(textField);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return pane;
    }
}