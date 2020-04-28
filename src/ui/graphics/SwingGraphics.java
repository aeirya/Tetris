package ui.graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controllers.GameState;
import controllers.UiManager;
import models.Architect;
import ui.panels.GamePanel;
import ui.panels.SidePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

public class SwingGraphics implements IGameGraphics {

    private final JFrame frame = new JFrame();
    private final JPanel mainPanel = new JPanel();
    private GamePanel gamePanel;
    private SidePanel sidePanel;
    private final UiManager uiManager = new UiManager(frame, mainPanel, gamePanel, sidePanel);
    
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
        frame.setResizable(false);
        mainPanel.setBackground(new Color(20,20,20));
    }
    
    public void setupLayoutManager(Dimension size) {
        int gameX = (int)(size.width * 0.75);
        int gameY = size.height;
        Architect.getInstance().updateNumbers(new Dimension(gameX, gameY));
        gamePanel = new GamePanel(gameX, gameY);
        sidePanel = new SidePanel(size.width-gameX, size.height);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        gamePanel.addToPanel(mainPanel, BoxLayout.X_AXIS);
        sidePanel.addToPanel(mainPanel, BoxLayout.X_AXIS);
    }
    
    public void update(GameState state) {
        gamePanel.update(state);
        sidePanel.update(state);
    }

    public void addKeyListener(KeyListener l) {
        mainPanel.addKeyListener(l);
        mainPanel.requestFocusInWindow();
    }

    @Override
    public void showMenu() {
        uiManager.showMenu();
    }
}