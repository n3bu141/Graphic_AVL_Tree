import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static final int centerX = 500;
    private static final int centerY = 15;
    private static final int trueCX = 512;
    private static final int trueCY = 27;
    private static final int outerD = 24;
    private static final int innerD = 20;
    private static final int radDif = 2;
    private final AVLTree tree;
    private boolean submit;
    private JButton button;
    private JLabel label;
    private JTextField text;

    public Panel() {
        setLayout(null);
        tree = new AVLTree();
        submit = false;
        button = new JButton("Submit");
        label = new JLabel("Enter in numerical values 10-99 (other values are possible, they just won't be centered) separated by spaces");
        text = new JTextField();
        button.addActionListener(e -> setSubmit());

        label.setBounds(10, 10, label.getPreferredSize().width, label.getPreferredSize().height);
        text.setBounds(10, 28, 500, text.getPreferredSize().height);
        button.setBounds(10, 50, button.getPreferredSize().width, button.getPreferredSize().height);

        add(label);
        add(text);
        add(button);
    }

    public void setSubmit() {
        String[] numbers = text.getText().split(" ");
        for (String x : numbers) {
            tree.add(Integer.parseInt(x));
        }

        remove(label);
        remove(text);
        remove(button);

        submit = true;
        repaint();
    }

    public void addNum(int x) {
        tree.add(x);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.fillOval(500, 15, 24, 24);
//        g.setColor(Color.WHITE);
//        g.fillOval(502, 17, 20, 20);
//        g.setColor(Color.black);
//        g.drawString(tree.getRoot().getValue().toString(), 505, 32);
        if (submit) {
            tree.assignOffsets();

            paintLines(g, tree.getRoot());
            paintNodes(g, tree.getRoot());
        }
    }

    public void paintLines(Graphics g, BinaryNode x) {
        g.setColor(Color.BLACK);

        if (x.getOffset() == 0) {
            if (x.left() != null) {
                g.drawLine(trueCX, trueCY, trueCX + centerOffset(x.left()), trueCY + x.left().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.left());
            }
            if (x.right() != null) {
                g.drawLine(trueCX, trueCY, trueCX + centerOffset(x.right()), trueCY + x.right().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.right());
            }
        } else if (x.getOffset() < 0) {
            if (x.left() != null) {
                g.drawLine(trueCX + centerOffset(x) + ((x.getOffset() + 1) * 2 * Math.abs(centerOffset(x))), trueCY + x.getLevel() * Frame.HEIGHT / 10, trueCX + centerOffset(x.left()) + ((x.left().getOffset() + 1) * 2 * Math.abs(centerOffset(x.left()))), trueCY + x.left().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.left());
            }

            if (x.right() != null) {
                g.drawLine(trueCX + centerOffset(x) + ((x.getOffset() + 1) * 2 * Math.abs(centerOffset(x))), trueCY + x.getLevel() * Frame.HEIGHT / 10, trueCX + centerOffset(x.right()) + ((x.right().getOffset() + 1) * 2 * Math.abs(centerOffset(x.right()))), trueCY + x.right().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.right());
            }
        } else if (x.getOffset() > 0) {
            if (x.left() != null) {
                g.drawLine(trueCX + centerOffset(x) + ((x.getOffset() - 1) * 2 * Math.abs(centerOffset(x))), trueCY + x.getLevel() * Frame.HEIGHT / 10, trueCX + centerOffset(x.left()) + ((x.left().getOffset() - 1) * 2 * Math.abs(centerOffset(x.left()))), trueCY + x.left().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.left());
            }

            if (x.right() != null) {
                g.drawLine(trueCX + centerOffset(x) + ((x.getOffset() - 1) * 2 * Math.abs(centerOffset(x))), trueCY + x.getLevel() * Frame.HEIGHT / 10, trueCX + centerOffset(x.right()) + ((x.right().getOffset() - 1) * 2 * Math.abs(centerOffset(x.right()))), trueCY + x.right().getLevel() * Frame.HEIGHT / 10);
                paintLines(g, x.right());
            }
        }

//        if (x.left() != null) {
//            System.out.println(x.left().getOffset());
//            g.drawLine(centerX + centerOffset(x) * (x.getOffset() + 1) + outerD / 2, centerY + x.getLevel() * Frame.HEIGHT / 10 + outerD / 2, centerX + centerOffset(x.left()) * (x.left().getOffset()) + outerD / 2, centerY + x.left().getLevel() * Frame.HEIGHT / 10 + outerD / 2);
//            paintLines(g, x.left());
//        }
//
//        if (x.right() != null) {
//            System.out.println(x.right().getOffset());
//            g.drawLine(centerX + centerOffset(x) + x.getOffset() * Frame.WIDTH / (int) (Math.pow(2, x.getLevel() + 1)) + outerD / 2, centerY + x.getLevel() * Frame.HEIGHT / 10 + outerD / 2, centerX + centerOffset(x.right()) * x.right().getOffset() + outerD / 2, centerY + x.right().getLevel() * Frame.HEIGHT / 10 + outerD / 2);
//            paintLines(g, x.right());
//        }
    }

    public void paintNodes(Graphics g, BinaryNode x) {
        if (x == null) {
            return;
        }

        paintNodes(g, x.left());
        paintNodes(g, x.right());

        if (x == tree.getRoot()) {
            g.fillOval(centerX, centerY, outerD, outerD);
            g.setColor(Color.WHITE);
            g.fillOval(centerX + radDif, centerY + radDif, innerD, innerD);
            g.setColor(Color.black);
            g.drawString(tree.getRoot().getValue().toString(), centerX + 5, centerY + 17);

            paintNodes(g, x.left());
            paintNodes(g, x.right());

            return;
        }

        if (x.getOffset() < 0) {
            g.fillOval(centerX + centerOffset(x) + ((x.getOffset() + 1) * 2 * Math.abs(centerOffset(x))), centerY + x.getLevel() * Frame.HEIGHT / 10, outerD, outerD);
            g.setColor(Color.WHITE);
            g.fillOval(centerX + radDif + centerOffset(x) + ((x.getOffset() + 1) * 2 * Math.abs(centerOffset(x))), centerY + radDif + x.getLevel() * Frame.HEIGHT / 10, innerD, innerD);
            g.setColor(Color.black);
            g.drawString(x.getValue().toString(), centerX + 5 + centerOffset(x) + ((x.getOffset() + 1) * 2 * Math.abs(centerOffset(x))), centerY + 17 + x.getLevel() * Frame.HEIGHT / 10);
        }
        if (x.getOffset() > 0) {
            g.fillOval(centerX + centerOffset(x) + ((x.getOffset() - 1) * 2 * Math.abs(centerOffset(x))), centerY + x.getLevel() * Frame.HEIGHT / 10, outerD, outerD);
            g.setColor(Color.WHITE);
            g.fillOval(centerX + radDif + centerOffset(x) + ((x.getOffset() - 1) * 2 * Math.abs(centerOffset(x))), centerY + radDif + x.getLevel() * Frame.HEIGHT / 10, innerD, innerD);
            g.setColor(Color.black);
            g.drawString(x.getValue().toString(), centerX + 5 + centerOffset(x) + ((x.getOffset() - 1) * 2 * Math.abs(centerOffset(x))), centerY + 17 + x.getLevel() * Frame.HEIGHT / 10);

        }
    }

    public int centerOffset(BinaryNode x) {
        if (x.getOffset() == 0) {
            return 0;
        }

        if (x.getOffset() < 0) {
            return Math.negateExact((int) (Frame.WIDTH / Math.pow(2, x.getLevel() + 1)));
        } else {
            return (int) (Frame.WIDTH / Math.pow(2, x.getLevel() + 1));
        }
    }
}
