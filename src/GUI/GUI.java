package GUI;


import ActionListeners.CreatingAminos;
import ActionListeners.CreatingCompareMode;


import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private JButton but = new JButton("submit");
    private static JTextField tf = new JTextField(50);
    private JPanel panelTop = new JPanel();
    private JPanel panelMiddle = new JPanel();
    private static JPanel panelBottom = new JPanel();

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout(1, 1, 1));

        addingCompsRight();

        this.getContentPane().add(panelTop);
        this.getContentPane().add(panelMiddle);
        this.getContentPane().add(panelBottom);

        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public JTextField getTf() {
        return tf;
    }

    public JPanel getPanelBottom() {
        return panelBottom;
    }


    private void addingCompsRight() {
        panelTop.setBackground(Color.BLACK);
        panelTop.setLayout(new FlowLayout());
        panelTop.setPreferredSize(new Dimension(900, 100));
        tf.addKeyListener(new CreatingCompareMode());
        panelTop.add(tf);

        panelMiddle.setBackground(Color.BLACK);
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.setPreferredSize(new Dimension(900, 100));
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        panelMiddle.add(but);

        panelBottom.setBackground(Color.LIGHT_GRAY);
        panelBottom.setLayout(null);
        panelBottom.setPreferredSize(new Dimension(900, 500));
    }
}
