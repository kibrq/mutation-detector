package GUI;


import ActionListeners.CreatingAminos;
import ActionListeners.CreatingModes;
import ActionListeners.Scrolling;


import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private JButton but = new JButton("Submit");
    private JTextField inputSeq = new JTextField(50);
    private JTextField inputDM = new JTextField(8);
    private JPanel aminoPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(aminoPanel);
    private JPanel panelUnderBottom = new JPanel();
    private static JPanel panelTop = new JPanel();

    private JPanel panelMiddle = new JPanel();
    private JPanel panelBottom = new JPanel();

    private JPanel firstAmino = new JPanel();
    private JPanel panelLines = new JPanel();
    private JPanel secondAmino = new JPanel();

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout(1, 1, 1));
        inputSeq.addKeyListener(new CreatingModes());
        addingCompsRight();
        settingPanelTop();
        this.getContentPane().add(panelTop);
        this.getContentPane().add(panelMiddle);
        this.getContentPane().add(panelBottom);
        this.getContentPane().add(panelUnderBottom);

        this.setSize(900, 700);
        this.addKeyListener(new CreatingModes());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public JTextField getInputDM() {
        return inputDM;
    }

    public JTextField getTf() {
        return inputSeq;
    }

    public JPanel getPanelBottom() {
        return panelBottom;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getAminoPanel() {
        return aminoPanel;
    }

    public void settingPanelTop() {
        panelTop.removeAll();
        panelTop.setBackground(Color.BLACK);
        panelTop.setLayout(new FlowLayout());
        panelTop.setPreferredSize(new Dimension(900, 100));
        JLabel label = new JLabel("Input sequence:");
        label.setForeground(Color.white);
        panelTop.add(label);
        panelTop.add(inputSeq);
        JLabel label1 = new JLabel("Input dM:");
        label1.setForeground(Color.white);
        panelTop.add(label1);
        panelTop.add(inputDM);
    }

    private void addingCompsRight() {


        panelMiddle.setBackground(Color.BLACK);
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.setPreferredSize(new Dimension(900, 100));
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        but.addKeyListener(new CreatingModes());
        panelMiddle.add(but);


        panelBottom.setBackground(Color.LIGHT_GRAY);
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setPreferredSize(new Dimension(900, 70));
        scrollPane.setPreferredSize(new Dimension(250, 50));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new Scrolling());
        aminoPanel.setLayout(new FlowLayout(1, 10, 0));
        panelBottom.add(scrollPane);

        panelUnderBottom.setBackground(Color.LIGHT_GRAY);
        panelUnderBottom.setPreferredSize(new Dimension(900, 430));
        panelUnderBottom.setLayout(new FlowLayout(1, 0, 1));
        firstAmino.setPreferredSize(new Dimension(30, 430));
        firstAmino.setBackground(panelBottom.getBackground());
        panelLines.setPreferredSize(new Dimension(100, 430));
        panelLines.setBackground(panelBottom.getBackground());
        secondAmino.setPreferredSize(new Dimension(30, 430));
        secondAmino.setBackground(panelBottom.getBackground());
        panelLines.setLayout(new BorderLayout());

        panelUnderBottom.add(firstAmino);
        panelUnderBottom.add(panelLines);
        panelUnderBottom.add(secondAmino);


    }

    public JPanel getFirstAmino() {
        return firstAmino;
    }

    public JPanel getPanelLines() {
        return panelLines;
    }

    public JPanel getSecondAmino() {
        return secondAmino;
    }

    public JPanel getPanelUnderBottom() {
        return panelUnderBottom;
    }
}
