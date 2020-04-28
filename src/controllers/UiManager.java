package controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panels.GamePanel;
import ui.panels.Menu;
import ui.panels.Panel;
import ui.panels.SidePanel;

public class UiManager {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final Panel gamePanel;
    private final Panel sidePanel;
    private final Menu menu;

    public UiManager(JFrame frame, JPanel mainPanel, Panel gamePanel, Panel sidePanel) {
        this.frame = frame;
        this.mainPanel = mainPanel;
        this.gamePanel = gamePanel;
        this.sidePanel = sidePanel;
        menu = new Menu();
    }
    
    public void showMenu() {
        frame.setSize(frame.getSize().width, (int)(frame.getSize().height*));
        frame.setLayout(new BorderLayout());
        // frame.setContentPane(menu);
        // frame.add(menu, BorderLayout.CENTER);
        
        menu.setPreferredSize(new Dimension(frame.getSize().width, (int) (frame.getSize().height * 0.2)));
        menu.setSize(menu.getPreferredSize());
        frame.getContentPane().add(menu, BorderLayout.SOUTH);
        
        frame.setResizable(true);
        // menu.setBounds(10,600,100,100);
        // frame.getContentPane().add(new JButton("FRICK"), BorderLayout.CENTER);
        frame.revalidate();
    }

    public void hideMenu() {
        //
    }

    public void toggleMenu() {
        
    }
}