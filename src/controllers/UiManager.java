package controllers;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.panels.Menu;
import ui.panels.Panel;

public class UiManager {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final Panel gamePanel;
    private final Panel sidePanel;
    private final Menu menu;
    
    // private final int width;
    // private final int height;

    public UiManager(JFrame frame, JPanel mainPanel, Panel gamePanel, Panel sidePanel) {
        // width = frame.get
        // height = frame.getHeight();
        this.frame = frame;
        this.mainPanel = mainPanel;
        this.gamePanel = gamePanel;
        this.sidePanel = sidePanel;
        menu = new Menu();
        addMenu();
    }

    private void addMenu() {
        // frame.setLayout(new BorderLayout());
        // frame.getContentPane().add(menu, BorderLayout.SOUTH);
        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        frame.add(menu, BoxLayout.Y_AXIS);
        menu.setPreferredSize(new Dimension(frame.getWidth(), 100));
        menu.setSize(menu.getPreferredSize());
    }
    
    public void showMenu() {
        frame.setSize(frame.getSize().width, (int)(frame.getSize().height + 100));
        // frame.pack();
        menu.setSize(menu.getPreferredSize());
        
        frame.setResizable(true);
        frame.revalidate();
    }

    public void hideMenu() {
        //
    }

    public void toggleMenu() {
//
    }
}