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
    private CreatingCompareMode ccm = new CreatingCompareMode();

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new FlowLayout(1, 1, 1));
        addingCompsRight();
        this.getContentPane().add(panelTop);
        this.getContentPane().add(panelMiddle);
        this.getContentPane().setFocusable(true);
        this.getContentPane().addKeyListener(ccm);
    }

    public JTextField getTf() {
        return tf;
    }

    public JPanel getPanelBottom() {
        return panelBottom;
    }

    public void showGui() {
        this.setVisible(false);
        panelBottom.addKeyListener(ccm);
        this.getContentPane().add(panelBottom);
        this.getContentPane().setFocusable(true);
        this.setVisible(true);
    }


    private void addingCompsRight() {
        panelTop.setBackground(Color.BLACK);
        panelMiddle.setBackground(Color.BLACK);

        panelMiddle.addKeyListener(ccm);
        panelTop.addKeyListener(ccm);
        panelTop.setLayout(new FlowLayout());
        panelTop.setPreferredSize(new Dimension(900, 100));
        tf.addKeyListener(ccm);
        panelTop.add(tf);
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.setPreferredSize(new Dimension(900, 100));
        panelBottom.setBackground(Color.LIGHT_GRAY);
        panelBottom.setLayout(null);
        panelBottom.setPreferredSize(new Dimension(900, 500));
        but.addKeyListener(ccm);
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        panelMiddle.add(but);
    }
}
