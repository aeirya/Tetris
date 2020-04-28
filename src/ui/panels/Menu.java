package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import app.Game;
import app.Tetris;

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
        int i = 0;
        actions.forEach(btn.get(i++)::addActionListener);
        // btn.forEach(action);
        return box;
        list.forEach(
            (text, listener) -> {
                new JButton(text).addActionListener(listener);
            }
        );
    }

    private transient Game game = Game.getInstance();
    private final transient Map <String, ActionListener> list = Map.of(
        "Sorry", 
        (ActionEvent e) -> {
    
        },
        "Restart",
        (ActionEvent e) -> {
            game.reset();
        },
        "Save",
        (ActionEvent e) -> {
            game.load();
        },
        "Quit",
        (ActionEvent e) -> {
            game.save();
        },
        "Load",
        (ActionEvent e) -> {
            Tetris.quitGame();
        }

    );

    public void unfocusButton(JButton b) {
        b.setFocusable(false);
    }

    public void addActionListener(JButton b) {
        // list.
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