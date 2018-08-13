package GUI;


import Global.*;
import Listeners.ActionListners.SubmitButton.CreatingAminos;
import Listeners.KeyListeners.CreatingModes;
import Listeners.KeyListeners.PrefixSuffixSelect;
import Listeners.KeyListeners.Scrolling;

import javax.swing.*;
import java.awt.*;


public class GUI extends JPanel {
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

    private JPanel panelUnderBottomLeft = new JPanel();
    private JPanel panelUnderBottomRight = new JPanel();

    private JPanel firstAmino = new JPanel();
    private JPanel panelLines = new JPanel();
    private JPanel secondAmino = new JPanel();


    private JPanel navigationPanel1 = new JPanel();


    public GUI() {
        this.setSize(Start.frame.getWidth(), Start.frame.getHeight());

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

        inputSeq.addKeyListener(new CreatingModes());
        inputDM.addKeyListener(new CreatingModes());
        inputPPM.addKeyListener(new CreatingModes());

        inputSeq.addKeyListener(new PrefixSuffixSelect());
        inputDM.addKeyListener(new PrefixSuffixSelect());
        inputPPM.addKeyListener(new PrefixSuffixSelect());

        addingCompsRight();
        settingPanelTop();
        fillingUnderBottomPanel(false);

        this.add(panelTop);
        this.add(panelMiddle);
        this.add(panelBottom);
        this.add(panelUnderBottom);

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
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
        panelTop.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 11));
        JLabel label = new JLabel("Sequence:  ");
        label.setForeground(Color.white);
        panelTop.add(label);
        panelTop.add(inputSeq);
        JLabel label1 = new JLabel("  dM:  ");
        label1.setForeground(Color.white);
        panelTop.add(label1);
        panelTop.add(inputDM);
        JLabel label2 = new JLabel("  ppm:  ");
        label2.setForeground(Color.WHITE);
        panelTop.add(label2);
        panelTop.add(inputPPM);
    }

    private void addingCompsRight() {

        panelMiddle.setBackground(Color.BLACK);
        panelMiddle.setLayout(new FlowLayout());
        panelMiddle.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 14));
        but.setPreferredSize(new Dimension(100, 20));
        but.addActionListener(new CreatingAminos());
        but.addKeyListener(new CreatingModes());
        but.addKeyListener(new PrefixSuffixSelect());
        panelMiddle.add(but);


        panelBottom.setBackground(Color.LIGHT_GRAY);
        panelBottom.setLayout(new FlowLayout());
        panelBottom.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 11));
        scrollPane.setPreferredSize(new Dimension(Variables.buttonWidth * Variables.countInScrollPanel + 10, 50));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new Scrolling());
        aminoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBottom.add(scrollPane);

        panelUnderBottom.setBackground(Color.LIGHT_GRAY);
        panelUnderBottom.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - panelTop.getHeight() - panelMiddle.getHeight() - panelBottom.getHeight()));
        panelUnderBottom.setLayout(new FlowLayout(FlowLayout.CENTER, -20, 0));
        firstAmino.setPreferredSize(new Dimension(30, 4 * this.getHeight() / 5));
        firstAmino.setBackground(panelBottom.getBackground());
        panelLines.setPreferredSize(new Dimension(200, 4 * this.getHeight() / 5));
        panelLines.setBackground(panelBottom.getBackground());
        secondAmino.setPreferredSize(new Dimension(35, 4 * this.getHeight() / 5));
        secondAmino.setBackground(panelBottom.getBackground());

        panelUnderBottomLeft.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() - panelTop.getHeight() - panelMiddle.getHeight() - panelBottom.getHeight()));
        panelUnderBottomLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 5));

        panelUnderBottomLeft.setBackground(panelBottom.getBackground());

        panelLines.setLayout(new BorderLayout());

        panelUnderBottomLeft.add(firstAmino);
        panelUnderBottomLeft.add(panelLines);
        panelUnderBottomLeft.add(secondAmino);

        navigationPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        navigationPanel1.setBackground(panelBottom.getBackground());
        navigationPanel1.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() / 7));

        panelUnderBottomRight.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight() - panelTop.getHeight() - panelMiddle.getHeight() - panelBottom.getHeight()));
        panelUnderBottomRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelUnderBottomRight.setBackground(panelBottom.getBackground());

        panelUnderBottomLeft.add(navigationPanel1);


    }

    public void fillingUnderBottomPanel(boolean addPanelUnderBottomRight) {

        panelUnderBottom.add(panelUnderBottomLeft);

        if (addPanelUnderBottomRight) {
            panelUnderBottom.add(panelUnderBottomRight);
        }


    }


    public JPanel getNavigationPanel() {
        return navigationPanel1;
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

    public JPanel getPanelUnderBottomRight() {
        return panelUnderBottomRight;
    }
}
