package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class SwingGraphics implements IGameGraphics {

    private final JFrame frame = new JFrame();
    private final JPanel mainPanel = new JPanel();
    private Architect arch;
    ui.Architect.Box box;

    @Override
    public void paint() {
        //
    }
    //paint() is called every frame
    
    public void start() {
        frame.setVisible(true);
    }
    
    public void refresh() {
        mainPanel.validate();
    }
    
    //creates the window, adds the main panel to it.
    public void setupFrame(Dimension size) {
        frame.setTitle("Tetris ^v^");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setBackground(new Color(20,20,20));
        mainPanel.setLayout(null);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
    }

    public void setupLayoutManager(Dimension size) {
        arch = new Architect(size);
    }    
}