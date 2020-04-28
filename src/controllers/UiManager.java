package controllers;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.panels.Menu;
import ui.panels.Panel;
import java.awt.BorderLayout;
import java.awt.Color;

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
        menu.setPreferredSize(new Dimension(frame.getWidth(), 35));
    }

    private static final int MENU_HEIGHT = 35;

    public void showMenu() {
        frame.setSize(frame.getSize().width, frame.getSize().height + MENU_HEIGHT);
        frame.add(menu , BorderLayout.SOUTH);
        frame.revalidate();
    }

    public void hideMenu() {
        frame.setSize(frame.getSize().width, frame.getSize().height - MENU_HEIGHT);
        frame.remove(menu);
    }

    public void toggleMenu() {
        // if (menu.Trigger) showMenu();
        
        else hideMenu();
    }
}