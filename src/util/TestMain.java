package util;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class TestMain {
    static TestBackup t;
    public static void main(String[] args) {
        // t = new TestMain().new TestBackup(1);
        // t.hold(2);
        // pop();
        // pop();
        new TestMain().splitter();
    }
    
    static void pop() {
        System.out.println(t.restore());
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

    
    void c() {
        JFrame frame = new JFrame();
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

    void splitter() {
        String s ="models.tetrimino.shapes.SShapedBox" ;
        String[] list = s.split("\\."); 
        System.out.println(list[list.length-1]); 
    }


    public class TestBackup {

        private int inQueue;
        private int currentSave;

        public TestBackup(int state) {
            currentSave = state;
            inQueue = state;
        }
        
        public void hold(int state) {
            push();
            queue(state);
        }
        
        private void queue(int state) {
            inQueue = state;
        }

        private int push() {
            int pop = currentSave;
            currentSave = inQueue;
            return pop;
        }

        public int restore() {
            int pop = push();
            if (pop == 0) util.log.GameLogger.log("pushing harder :))");
            return (pop != 0) ? pop : push();
        }
    }
}