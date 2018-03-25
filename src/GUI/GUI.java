package GUI;


import Listeners.ActionListners.SubmitButton.CreatingAminos;
import Listeners.KeyListeners.CreatingModes;
import Listeners.KeyListeners.PrefixSuffixSelect;
import Listeners.KeyListeners.Scrolling;
import Global.*;

import javax.swing.*;
import java.awt.*;



public class GUI extends JFrame {
    private JPanel panelTop = new JPanel();
    private JTextField inputSeq = new JTextField(50);
    private JTextField inputDM = new JTextField(8);
    private JTextField inputPPM = new JTextField(5);

    private JButton but = new JButton("Submit");

    private JPanel panelMiddle = new JPanel();
    private JPanel aminoPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(aminoPanel);

    private JPanel panelUnderBottom = new JPanel();
    private JPanel panelBottom = new JPanel();

    private JPanel firstAmino = new JPanel();
    private JPanel panelLines = new JPanel();
    private JPanel secondAmino = new JPanel();

    private JPanel navigationPanel = new JPanel();


    public GUI(String title) {
        super(title);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        inputSeq.addKeyListener(new CreatingModes());
        inputDM.addKeyListener(new CreatingModes());
        inputPPM.addKeyListener(new CreatingModes());

        inputSeq.addKeyListener(new PrefixSuffixSelect());
        inputDM.addKeyListener(new PrefixSuffixSelect());
        inputPPM.addKeyListener(new PrefixSuffixSelect());

        addingCompsRight();
        settingPanelTop();

        this.getContentPane().add(panelTop);
        this.getContentPane().add(panelMiddle);
        this.getContentPane().add(panelBottom);
        this.getContentPane().add(panelUnderBottom);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public JButton getBut() {
        return but;
    }

    public JTextField getInputPPM() {
        return inputPPM;
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
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 1 ,0));
        panelTop.setPreferredSize(new Dimension(this.getWidth(), 100));
        JLabel label = new JLabel("Sequence:  ");
        label.setForeground(Color.white);
        panelTop.add(label);
        panelTop.add(inputSeq);
        JLabel label1 = new JLabel("  dM:  ");
        label1.setForeground(Color.white);
        panelTop.add(label1);
        panelTop.add(inputDM);
        JLabel  label2 = new JLabel("  ppm:  ");
        label2.setForeground(Color.WHITE);
        panelTop.add(label2);
        panelTop.add(inputPPM);
    }

    private void addingCompsRight() {


        panelMiddle.setBackground(Color.BLACK);
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.setPreferredSize(new Dimension(this.getWidth(),100));
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        but.addKeyListener(new CreatingModes());
        but.addKeyListener(new PrefixSuffixSelect());
        panelMiddle.add(but);


        panelBottom.setBackground(Color.LIGHT_GRAY);
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setPreferredSize(new Dimension(this.getWidth(), 70));
        scrollPane.setPreferredSize(new Dimension(Variables.buttonWidth* Variables.countInScrollPanel + 10, 50));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new Scrolling());
        aminoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBottom.add(scrollPane);

        panelUnderBottom.setBackground(Color.LIGHT_GRAY);
        panelUnderBottom.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()-panelTop.getHeight()-panelMiddle.getHeight()-panelBottom.getHeight()));
        panelUnderBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
        firstAmino.setPreferredSize(new Dimension(30, 330));
        firstAmino.setBackground(panelBottom.getBackground());
        panelLines.setPreferredSize(new Dimension(200, 330));
        panelLines.setBackground(panelBottom.getBackground());
        secondAmino.setPreferredSize(new Dimension(35, 330));
        secondAmino.setBackground(panelBottom.getBackground());
        panelLines.setLayout(new BorderLayout());
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        navigationPanel.setBackground(panelBottom.getBackground());
        navigationPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()/7));


        panelUnderBottom.add(firstAmino);
        panelUnderBottom.add(panelLines);
        panelUnderBottom.add(secondAmino);
        panelUnderBottom.add(navigationPanel);


    }


    public JPanel getNavigationPanel() {
        return navigationPanel;
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
