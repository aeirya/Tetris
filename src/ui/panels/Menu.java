package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;

    private boolean isVisible = false;

    public Menu() {
        setLayout(new BorderLayout());
        setBackground(new Color(130,150,130));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        initiateComponents();
    }
    
    private Box buttons() {
        Box box = Box.createHorizontalBox();
        JButton sorry = new JButton("Sorry!");
        JButton restart = new JButton("Restart");
        JButton save = new JButton("Save");
        JButton quit = new JButton("Quit");
        JButton load = new JButton("Load");
        List<JButton> btn = 
            Arrays.asList(
                sorry, restart, load, save, quit
            );
        btn.forEach(this::unfocusButton); //idk why it didn't accept consumer expression
        btn.forEach(box::add);
        return box;
    }

    public void unfocusButton(JButton b) {
        b.setFocusable(false);
    }

    
    public void initiateComponents() {
        add(Box.createGlue());
        add(buttons());
        add(Box.createGlue());
        add(Box.createGlue());
    }

    public boolean trigger() {
        isVisible = !isVisible;
        return isVisible;
    }
}