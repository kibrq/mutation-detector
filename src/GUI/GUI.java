package GUI;


import Global.*;
import Listeners.ActionListners.AminoButton.CreatingCodons;
import Listeners.KeyListeners.ChangePPM;
import Listeners.KeyListeners.Scrolling;
import Model.AminoAcid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JPanel {
    private JSplitPane splitPane = new JSplitPane();

    private JPanel panelLeft = new JPanel();

    private JPanel panelLeftTop = new JPanel();
    private JPanel aminoSequence = new JPanel();

    private JPanel panelLeftCenter = new JPanel();

    private JPanel panelWithScrollPane = new JPanel();
    private JPanel panelWithAminoButtons = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panelWithAminoButtons);

    private JSplitPane splitPaneCodMod = new JSplitPane();
    private JPanel panelWithCodons = new JPanel();

    private JPanel panelWithModifications = new JPanel();

    private JPanel panelWithNavigationButton = new JPanel();
    private JButton next = new JButton();

    private JPanel panelRight = new JPanel();

    private JPanel panelRightTop = new JPanel();
    private JLabel massDifference = new JLabel();
    private JTextField mistakePPM = new JTextField();
    private JButton changePPM = new JButton();
    private JLabel currentMode = new JLabel();
    private JLabel currentMistake = new JLabel();


    public GUI() {
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        this.setLayout(new BorderLayout());
        setSplitPane();

    }

    private void setSplitPane() {
        splitPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.95);

        setPanelLeft();
        setPanelRight();


        this.add(splitPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setPanelLeft() {
        panelLeft.setLayout(new BorderLayout());

        setPanelLeftTop();
        setPanelLeftCenter();


        panelLeft.add(panelLeftTop, BorderLayout.NORTH);
        panelLeft.add(panelLeftCenter, BorderLayout.CENTER);

        splitPane.setLeftComponent(panelLeft);
        splitPane.getLeftComponent().setMinimumSize(new Dimension(200, 300));
    }

    private void setPanelLeftTop() {
        JPanel panel1 = new JPanel();
        panelLeftTop.setLayout(new BoxLayout(panelLeftTop, BoxLayout.X_AXIS));
        JLabel label1 = new JLabel("Sequence:");
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        aminoSequence.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
        panel1.add(label1);
        panel1.add(aminoSequence);
        panelLeftTop.add(Box.createHorizontalGlue());
        panelLeftTop.add(panel1);
        panelLeftTop.add(Box.createHorizontalGlue());
    }

    private void setPanelLeftCenter() {
        splitPaneCodMod.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneCodMod.setResizeWeight(0.55);

        panelLeftCenter.setLayout(new BorderLayout());
        panelWithScrollPane.setLayout(new BoxLayout(panelWithScrollPane, BoxLayout.Y_AXIS));


        panelWithAminoButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        scrollPane.setPreferredSize(new Dimension(305, 50));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelWithScrollPane.add(Box.createVerticalStrut(50));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(Box.createHorizontalGlue());
        panel1.add(scrollPane);
        panel1.add(Box.createHorizontalGlue());
        panelWithScrollPane.add(panel1);
        panelWithScrollPane.add(Box.createVerticalStrut(50));

        panelWithCodons.setPreferredSize(new Dimension(400, 300));
        panelWithCodons.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));


        panel2.add(Box.createVerticalStrut(40));
        panel2.add(panelWithCodons);
        panel2.add(Box.createVerticalStrut(40));
        setNavigationPanel();
        panel2.add(panelWithNavigationButton);
        panelWithModifications.setLayout(new BorderLayout());


        splitPaneCodMod.setLeftComponent(panel2);

        splitPaneCodMod.setRightComponent(panelWithModifications);


        panelLeftCenter.add(panelWithScrollPane, BorderLayout.NORTH);
        panelLeftCenter.add(splitPaneCodMod, BorderLayout.CENTER);


    }

    private void setNavigationPanel() {
        panelWithNavigationButton.setLayout(new BoxLayout(panelWithNavigationButton, BoxLayout.Y_AXIS));
        panelWithNavigationButton.add(Box.createVerticalStrut(10));
        next.setPreferredSize(new Dimension(80, 40));
        next.setText("NEXT");
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(Box.createHorizontalGlue());
        panel1.add(Box.createHorizontalStrut(250));
        next.setVisible(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Variables.setCurrentCandidate(Variables.getCurrentCandidate() + 1);
                Variables.setA2(Variables.getCandidates().get(Variables.getCurrentAmino()).get(Variables.getCurrentCandidate()));
                panelWithCodons.getComponent(0).repaint();

            }
        });
        panel1.add(next);
        panel1.add(Box.createHorizontalStrut(250));
        panel1.add(Box.createHorizontalGlue());
        panelWithNavigationButton.add(panel1);
        panelWithNavigationButton.add(Box.createVerticalStrut(80));
    }


    private void setPanelRight() {
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

        setPanelRightTop();

        panelRight.add(panelRightTop);
        panelRight.add(Box.createVerticalStrut(600));

        splitPane.setRightComponent(panelRight);
    }

    private void setPanelRightTop() {
        Font leftLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font rightLabel = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        panelRightTop.setLayout(new BoxLayout(panelRightTop, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label1 = new JLabel("Mass Difference: ");
        label1.setFont(leftLabel);
        massDifference.setFont(rightLabel);
        massDifference.setText("23.04");
        panel1.add(label1);
        panel1.add(massDifference);

        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label2 = new JLabel("PPM Mistake: ");
        label2.setFont(leftLabel);
        mistakePPM.setColumns(4);
        mistakePPM.setFont(rightLabel);
        mistakePPM.addKeyListener(new ChangePPM());
        mistakePPM.setFocusable(false);
        JLabel label21 = new JLabel();
        label21.setText("ppm");
        label21.setFont(leftLabel);
        panel2.add(label2);
        panel2.add(mistakePPM);
        panel2.add(label21);
        changePPM.setText("Change");
        changePPM.setName("Change");
        changePPM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton tmp = (JButton) e.getSource();
                if (tmp.getName().equals("Change")) {
                    mistakePPM.setFocusable(true);
                    mistakePPM.grabFocus();
                    tmp.setName("Submit");
                    tmp.setText("Submit");

                } else {
                    tmp.setName("Change");
                    tmp.setText("Change");
                    Variables.setPpm(Double.parseDouble(Variables.getGui().getMistakePPM().getText()));
                    JPopupMenu pm = new JPopupMenu();
                    pm.add(new JLabel("ppm has been changed"));
                    pm.show(Variables.getGui().getMistakePPM(), -100, 50);
                    mistakePPM.setFocusable(false);
                }
            }
        });
        panel2.add(changePPM);

        panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label4 = new JLabel("Current mode: ");
        label4.setFont(leftLabel);
        currentMode.setFont(rightLabel);
        currentMode.setText("Viewing of codons");
        panel4.add(label4);
        panel4.add(currentMode);


        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label3 = new JLabel("Current mistake: ");
        label3.setFont(leftLabel);
        currentMistake.setFont(rightLabel);
        currentMistake.setText("Undefined");
        panel3.add(label3);
        panel3.add(currentMistake);

        panelRightTop.add(Box.createVerticalStrut(10));
        panelRightTop.add(panel1);
        panelRightTop.add(panel2);
        panelRightTop.add(Box.createVerticalStrut(60));
        panelRightTop.add(panel4);
        panelRightTop.add(panel3);


    }

    public void fillWithData() {
        double waterMass = 18.01057;
        String s = aminoSequence.getName();
        double[] arr = new double[s.length()];

        for (int i = 0; i < s.length(); i++) {
            AminoAcid tmp = AminoAcid.valueOf(s.charAt(i));
            if (i == 0) {
                arr[i] = tmp.getMass();
            } else {
                arr[i] = tmp.getMass() + arr[i - 1] - waterMass;
            }
            JLabel label1 = new JLabel();
            label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            label1.setForeground(Variables.getNormalColor());
            label1.setText(tmp.getTitle());
            aminoSequence.add(label1);


            JButton button = new JButton();
            button.setText(tmp.getTitle());
            button.setName(Integer.toString(i));
            button.setPreferredSize(new Dimension(Variables.getButtonWidth() - 5, 20));
            button.addActionListener(new CreatingCodons());
            panelWithAminoButtons.add(button);

        }
        Variables.setMassesPrefix(arr);
        massDifference.setText(massDifference.getName() + "Da");

        scrollPane.getHorizontalScrollBar().addAdjustmentListener(new Scrolling());
    }


    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public void setSplitPane(JSplitPane splitPane) {
        this.splitPane = splitPane;
    }

    public JPanel getPanelLeft() {
        return panelLeft;
    }

    public void setPanelLeft(JPanel panelLeft) {
        this.panelLeft = panelLeft;
    }

    public JPanel getPanelLeftTop() {
        return panelLeftTop;
    }

    public void setPanelLeftTop(JPanel panelLeftTop) {
        this.panelLeftTop = panelLeftTop;
    }

    public JPanel getAminoSequence() {
        return aminoSequence;
    }

    public void setAminoSequence(JPanel aminoSequence) {
        this.aminoSequence = aminoSequence;
    }

    public JPanel getPanelLeftCenter() {
        return panelLeftCenter;
    }

    public void setPanelLeftCenter(JPanel panelLeftCenter) {
        this.panelLeftCenter = panelLeftCenter;
    }

    public JPanel getPanelWithScrollPane() {
        return panelWithScrollPane;
    }

    public void setPanelWithScrollPane(JPanel panelWithScrollPane) {
        this.panelWithScrollPane = panelWithScrollPane;
    }

    public JPanel getPanelWithAminoButtons() {
        return panelWithAminoButtons;
    }

    public void setPanelWithAminoButtons(JPanel panelWithAminoButtons) {
        this.panelWithAminoButtons = panelWithAminoButtons;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JSplitPane getSplitPaneCodMod() {
        return splitPaneCodMod;
    }

    public void setSplitPaneCodMod(JSplitPane splitPaneCodMod) {
        this.splitPaneCodMod = splitPaneCodMod;
    }

    public JPanel getPanelWithCodons() {
        return panelWithCodons;

    }

    public void setPanelWithCodons(JPanel panelWithCodons) {
        this.panelWithCodons = panelWithCodons;
    }

    public JPanel getPanelWithModifications() {
        return panelWithModifications;
    }

    public void setPanelWithModifications(JPanel panelWithModifications) {
        this.panelWithModifications = panelWithModifications;
    }

    public JPanel getPanelWithNavigationButton() {
        return panelWithNavigationButton;
    }

    public void setPanelWithNavigationButton(JPanel panelWithNavigationButton) {
        this.panelWithNavigationButton = panelWithNavigationButton;
    }

    public JButton getNext() {
        return next;
    }

    public void setNext(JButton next) {
        this.next = next;
    }

    public JPanel getPanelRight() {
        return panelRight;
    }

    public void setPanelRight(JPanel panelRight) {
        this.panelRight = panelRight;
    }

    public JPanel getPanelRightTop() {
        return panelRightTop;
    }

    public void setPanelRightTop(JPanel panelRightTop) {
        this.panelRightTop = panelRightTop;
    }

    public JLabel getMassDifference() {
        return massDifference;
    }

    public void setMassDifference(JLabel massDifference) {
        this.massDifference = massDifference;
    }

    public JTextField getMistakePPM() {
        return mistakePPM;
    }

    public void setMistakePPM(JTextField mistakePPM) {
        this.mistakePPM = mistakePPM;
    }

    public JLabel getCurrentMistake() {
        return currentMistake;
    }

    public void setCurrentMistake(JLabel currentMistake) {
        this.currentMistake = currentMistake;
    }

    public JLabel getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(JLabel currentMode) {
        this.currentMode = currentMode;
    }

    public JButton getChangePPM() {
        return changePPM;
    }

    public void setChangePPM(JButton changePPM) {
        this.changePPM = changePPM;
    }
}
