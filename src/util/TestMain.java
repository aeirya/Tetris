package util;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controllers.GameState;
import controllers.UiManager;
import models.Architect;
import ui.panels.GamePanel;
import ui.panels.Menu;
import ui.panels.SidePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

public class TestMain {
    public static void main(String[] args) {
        new TestMain().c();
    }
    
    void a() {
        int[] a = new int[]{1,2,3};
        int[] b = a;
        b[0] = 0;
        System.out.println(a[0]);
    }

    static void b() {
        System.out.println("hello,bye,1000".split(",")[2]);
    }

    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new JPanel();
    private JPanel panelGreen = new JPanel();

    void c() {
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        frame.add(layeredPaneTest(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static JLayeredPane layeredPaneTest() {
        JLayeredPane lpane = new JLayeredPane();
        JPanel panelBlue = new JPanel();
        JPanel panelGreen = new JPanel();
        lpane.setBounds(0, 0, 600, 400);
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(0, 0, 600, 400);
        panelBlue.setOpaque(true);
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(200, 100, 100, 100);
        panelGreen.setOpaque(true);
        lpane.add(panelBlue, null, 0);
        lpane.add(panelGreen, null, 0);
        return lpane;
    }
}