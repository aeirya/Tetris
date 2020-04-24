package ui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;

public class ComponentGenerator {
    
    private int width;
    private int height;

    public ComponentGenerator(int w, int h) {
        width = w;
        height = h;
    }

    public JComponent board(int number, String text) {
        Box box = Box.createVerticalBox();
        JComponent label = label(text);
        box.add(label);
        box.add(coloredLabel(String.valueOf(number), Color.GRAY.darker()));
        return sandwich(box);
    }
    
    public JComponent label(String text) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        return panel;
    }
    
    public JComponent coloredLabel(String text, Color color) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        panel.setBackground(color);
        return panel;
    }

    public JComponent numberBox(int n) {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        JTextField text = new JTextField(String.valueOf(n), 1);
        text.setPreferredSize(
            new Dimension((int) (0.9 * width), (int) (0.05 * height))
            );
        text.setEditable(false);
        box.add(text);
        box.add(Box.createHorizontalGlue());
        return box;
    }
    
    public JComponent button(String text) {
        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(
            new Dimension((int) (0.6 * width), (int) (0.3 * height)
            ));   
        return sandwich(btn);
    } 

    public JComponent sandwich(JComponent component) {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(component);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    public JComponent vSandwich(JComponent component) {
        Box box = Box.createVerticalBox();
        box.add(component);
        box.add(verticalFiller(0.05, 0.05, 0.1));
        return box;
    }
            
    /** @param m : minimum height rate 
     * @param M : maximum height rate
     * @param p : preferred height rate
     * @apiNote rates are multiplied by the screen height
    */
    public JComponent verticalFiller(double m, double p, double M) {
        int boxWidth = (int) (0.9 * width);
        return new Box.Filler(
            new Dimension(boxWidth, (int) (m * height)),
            new Dimension(boxWidth, (int) (p * height)),
            new Dimension(boxWidth, (int) (M * height))
        );
    }
}