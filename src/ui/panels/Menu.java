package ui.panels;

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

public class Menu extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private static final List<JButton> btnList = new ArrayList<>();
    private static final transient
        Map <String, ActionListener> btnMap = Map.of(
            "Sorr(y)",
            (ActionEvent e) ->
                Game.getInstance().restore()
            ,
            "(R)estart",
            (ActionEvent e) -> 
                Game.getInstance().reset()
            ,
            "(L)oad",
            (ActionEvent e) -> 
                Game.getInstance().load()
            ,
            "Sa(v)e",
            (ActionEvent e) -> 
                Game.getInstance().save()
            ,
            "Qui(t)",
            (ActionEvent e) -> 
                Game.getInstance().quit()
        );

    public Menu(int w, int h) {
        setBackground(new Color(130,150,130));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(new Dimension(w,h));
        initiateComponents();
    }

    public void initiateComponents() {
        add(Box.createGlue());
        add(buttonsBox());
        add(Box.createGlue());
        add(Box.createGlue());
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
        return box;
    }
    
    private JButton makeButton(String text, ActionListener listener) {
        JButton btn = new JButton();
        btn.setText(text);
        btn.addActionListener(listener);
        return btn;
    }
}