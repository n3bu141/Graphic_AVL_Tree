import javax.swing.*;

public class Frame extends JFrame {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 800;
    private Panel panel;

    public Frame(){
        super("Binary Search Tree");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new Panel();
        add(panel);
        setVisible(true);
    }

    public void addNum(int x){
        panel.addNum(x);
    }
}
