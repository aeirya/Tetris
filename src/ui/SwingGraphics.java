package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ui.panels.GamePanel;
import ui.panels.SidePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class SwingGraphics implements IGameGraphics {

    private final JFrame frame = new JFrame();
    private final JPanel mainPanel = new JPanel();

    public void start() {
        frame.setVisible(true);
    }
    
    //paint() is called every frame
    public void paint() {
        mainPanel.repaint(); //redirects to paintCompnent in GamePanel
    }
    
    //creates the window, adds the main panel to it.
    public void setupFrame(Dimension size) {
        frame.setTitle("Tetris ^v^");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
        mainPanel.setBackground(new Color(20,20,20));
    }

    public void setupLayoutManager(Dimension size) {
        int gameX = (int)(size.width * 0.75);
        int gameY = size.height;
        Architect.getInstance().updateNumbers(new Dimension(gameX, gameY));
        GamePanel gamePanel = new GamePanel(gameX, gameY);
        SidePanel sidePanel = new SidePanel(size.width-gameX, size.height);
        mainPanel.setLayout(new java.awt.BorderLayout());
        gamePanel.addToPanel(mainPanel, BorderLayout.CENTER);
        sidePanel.addToPanel(mainPanel, BorderLayout.LINE_END);
    }
}