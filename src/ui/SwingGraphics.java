package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

public class SwingGraphics implements IGameGraphics {

    private final JFrame frame = new JFrame();
    private final JPanel mainPanel = new JPanel();
    private Architect arch;

    @Override
    public void paint() {
        System.out.println("paint :p");
        for (int i=0; i<2; i++) {
            // mainPanel.add(new JButton(String.valueOf(i)));
            mainPanel.add(new JPanel());
        }
    }
    
    public void refresh() {
        mainPanel.validate();
    }

    //creates the window, adds the main panel to it.
    public void setupFrame(Dimension size) {
        frame.setTitle("Tetris ^v^");
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel.setBackground(new Color(20,20,20));
        mainPanel.add(new JButton("HI"));
        frame.setVisible(true);
    }

    public void setupLayoutManager(Dimension size) {
        arch = new Architect(size);
    }    
}