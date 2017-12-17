package GUI;

import ActionListeners.CreateMenu;
import ActionListeners.CreatingAminos;


import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private JButton but = new JButton("submit");
    public static JTextField tf = new JTextField(50);
    private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private JPanel panelRight = new JPanel();
    private JPanel panelLeft = new JPanel();
    private JPanel panelTop = new JPanel();
    private JPanel panelMiddle = new JPanel();
    public static JPanel panelBottom = new JPanel();
    private String[] list = {"F1", "F2", "F3"};
    private JList menu = new JList(list);

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        addPanelLeft();
        addingCompsRight();
        addPanelRight();
        this.getContentPane().add(splitPane);
        this.setVisible(true);
    }

    private void creatingComboBox() {
        menu.addListSelectionListener(new CreateMenu());
    }

    private void addPanelLeft() {
        creatingComboBox();
        panelLeft.setBackground(Color.GRAY);
        panelLeft.add(menu);
        this.getContentPane().add(panelLeft);
        splitPane.add(panelLeft);
    }

    private void addPanelRight() {
        panelRight.setLayout(new GridLayout(3, 1));
        panelRight.add(panelTop);
        panelRight.add(panelMiddle);
        panelRight.add(panelBottom);
        splitPane.add(panelRight);
    }

    private void addingCompsRight() {
        panelTop.setBackground(Color.BLACK);
        panelMiddle.setBackground(Color.BLACK);
        panelBottom.setBackground(Color.DARK_GRAY);
        panelTop.setLayout(new FlowLayout());
        panelTop.add(tf);
        panelMiddle.setLayout(new FlowLayout());
        panelBottom.setLayout(new FlowLayout());
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        panelMiddle.add(but);
    }

    public static void main(String[] args) {
        new GUI("GUI");
    }
}
