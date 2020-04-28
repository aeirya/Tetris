package ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JPanel {

    private static final long serialVersionUID = 1L;

    public Menu() {
        setLayout(new BorderLayout());
        setBackground(new Color(30,30,40));
        this.add(initiateComponents(), BorderLayout.SOUTH);
    }
    
    public JComponent initiateComponents() {
        Box box = Box.createHorizontalBox();
        box.add(new JButton("HI"));
        box.add(new JButton("HI")); 
        box.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));
        box.setSize(this.getPreferredSize());
        return box;
    }
}