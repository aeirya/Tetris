package controllers.manager;

import javax.swing.JFrame;
import ui.panels.Menu;
import java.awt.BorderLayout;

public class UiManager {

    private final JFrame frame;
    
    private final Menu menu;

    public UiManager(JFrame frame) {
        this.frame = frame;
        menu = new Menu(frame.getWidth(), 35);
    }

    private static final int MENU_HEIGHT = 35;

    public void showMenu() {
        frame.setSize(frame.getSize().width, frame.getSize().height + MENU_HEIGHT);
        frame.add(menu, BorderLayout.SOUTH);
        frame.revalidate();
    }

    public void hideMenu() {
        frame.setSize(frame.getSize().width, frame.getSize().height - MENU_HEIGHT);
        frame.remove(menu);
    }

    public void toggleMenu() {
        if (menu.trigger()) showMenu();   
        else hideMenu();
    }
}