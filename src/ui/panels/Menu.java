package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import app.Game;

public class Menu extends Panel {
    
    private static final List<JButton> btnList = new ArrayList<>();
    private static final 
        Map <String, ActionListener> btnMap = Map.of(
            "Sorry (y)",
            (ActionEvent e) ->
                Game.getInstance().restore()
            ,
            "Restart (r)",
            (ActionEvent e) -> 
                Game.getInstance().reset()
            ,
            "Load (l)",
            (ActionEvent e) -> 
                Game.getInstance().load()
            ,
            "Save (k)",
            (ActionEvent e) -> 
                Game.getInstance().save()
            ,
            "Quit (p)",
            (ActionEvent e) -> 
                Game.getInstance().quit()
        );

    public Menu(int w, int h) {
        super(w, h);
        // setLayout(new BorderLayout());
        setBackground(new Color(130,150,130));
        setLayout(new BoxLayout(this.pane, BoxLayout.LINE_AXIS));
        setPreferredSize(w,h);
        initiateComponents();
    }

    public void initiateComponents() {
        pane.add(Box.createGlue());
        pane.add(buttonsBox());
        pane.add(Box.createGlue());
        pane.add(Box.createGlue());
    }

    private boolean isVisible = false;

    public boolean trigger() {
        isVisible = !isVisible;
        return isVisible;
    }
    
    private Box buttonsBox() {
        Box box = Box.createHorizontalBox();
        btnMap.forEach(
            (String text, ActionListener listener) -> 
                btnList.add(makeButton(text, listener))
            );
        btnList.forEach(btn -> btn.setFocusable(false));
        btnList.forEach(box::add);
        box.setPreferredSize(new Dimension(width , height));
        return box;
    }
    
    private JButton makeButton(String text, ActionListener listener) {
        JButton btn = new JButton();
        btn.setText(text);
        btn.addActionListener(listener);
        return btn;
    }
}