package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

public class SwingGraphics implements IGameGraphics {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    @Override
    public void setup() {
        setupFrame();
    }

    @Override
    public void paint() {
        //
    }

    //creates the window, adds the main panel to it.
    private void setupFrame() {
        frame.setTitle("Tetris ^v^");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        panel.setBackground(new Color(20,20,20));
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}