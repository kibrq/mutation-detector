package GUI;


import AdditionalClasses.UsefullFunctions;
import Global.*;
import Listeners.ActionListners.AminoButton.CreatingCodons;
import Listeners.KeyListeners.ChangePPM;
import Listeners.KeyListeners.Scrolling;
import Model.AminoAcid;
import MyComponents.JAminoAcidChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUI extends JPanel {
    private int rightPanelWidth = 400;

    private JSplitPane splitPane = new JSplitPane();

    private JPanel panelLeft = new JPanel();

    private JPanel panelLeftTop = new JPanel();
    private JPanel aminoSequence = new JPanel();

    private JPanel panelLeftCenter = new JPanel();

    private JPanel panelWithScrollPane = new JPanel();
    private JPanel panelWithAminoButtons = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panelWithAminoButtons);
    private JButton suffix = new JButton("Highlight suffix");
    private JButton prefix = new JButton("Highlight prefix");

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

    private JPanel panelRightBottom = new JPanel();
    private JAminoAcidChooser ac1;
    private JAminoAcidChooser ac2;


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

        panelLeftCenter.setBackground(Variables.getColorOfPanelWithCodonsModifications());
        panelLeft.setBackground(Variables.getColorOfPanelWithCodonsModifications());
        panelLeft.add(panelLeftTop, BorderLayout.NORTH);
        panelLeft.add(panelLeftCenter, BorderLayout.CENTER);

        splitPane.setLeftComponent(panelLeft);
        splitPane.getLeftComponent().setMinimumSize(new Dimension(900, 300));
    }

    private void setPanelLeftTop() {
        JPanel panel1 = new JPanel();
        panelLeftTop.setLayout(new BoxLayout(panelLeftTop, BoxLayout.X_AXIS));
        JLabel label1 = new JLabel("Sequence:");
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        aminoSequence.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
        aminoSequence.setBackground(Variables.getColorOfPanelWithSequnece());
        panel1.add(label1);
        panel1.add(aminoSequence);
        panelLeftTop.add(Box.createHorizontalGlue());
        panel1.setBackground(Variables.getColorOfPanelWithSequnece());
        panelLeftTop.add(panel1);
        panelLeftTop.add(Box.createHorizontalGlue());
        panelLeftTop.setBackground(Variables.getColorOfPanelWithSequnece());
    }

    private void setPanelLeftCenter() {
        splitPaneCodMod.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneCodMod.setResizeWeight(0.5);

        panelLeftCenter.setLayout(new BorderLayout());
        panelWithScrollPane.setLayout(new BoxLayout(panelWithScrollPane, BoxLayout.Y_AXIS));
        panelWithScrollPane.setBackground(Variables.getColorOfPanelWithSequnece());


        panelWithAminoButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        scrollPane.setPreferredSize(new Dimension(305, 50));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelWithScrollPane.add(Box.createVerticalStrut(50));
        JPanel panel1 = new JPanel();
        panel1.setBackground(Variables.getColorOfPanelWithSequnece());
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(Box.createHorizontalGlue());
        suffix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsefullFunctions.suffixAction();
            }
        });
        panel1.add(suffix);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(scrollPane);
        prefix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsefullFunctions.prefixAction();
            }
        });
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(prefix);
        suffix.setVisible(false);
        prefix.setVisible(false);
        panel1.add(Box.createHorizontalGlue());
        panelWithScrollPane.add(panel1);
        panelWithScrollPane.add(Box.createVerticalStrut(50));

        panelWithCodons.setPreferredSize(new Dimension(400, 300));
        panelWithCodons.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Variables.getColorOfPanelWithCodonsModifications());

        panelWithCodons.setBackground(Variables.getColorOfPanelWithCodonsModifications());
        panelWithModifications.setBackground(Variables.getColorOfPanelWithCodonsModifications());
        panel2.add(Box.createVerticalStrut(40));
        panel2.add(panelWithCodons);
        panel2.add(Box.createVerticalStrut(40));
        setNavigationPanel();
        panel2.add(panelWithNavigationButton);
        panelWithModifications.setLayout(new BorderLayout());


        splitPaneCodMod.setLeftComponent(panel2);

        splitPaneCodMod.setRightComponent(panelWithModifications);

        panelWithScrollPane.setBackground(Variables.getColorOfPanelWithSequnece());
        panelLeftCenter.add(panelWithScrollPane, BorderLayout.NORTH);
        panelLeftCenter.add(splitPaneCodMod, BorderLayout.CENTER);

        panelLeftCenter.setBackground(Variables.getColorOfPanelWithCodonsModifications());


    }

    private void setNavigationPanel() {
        panelWithNavigationButton.setLayout(new BoxLayout(panelWithNavigationButton, BoxLayout.Y_AXIS));
        panelWithNavigationButton.add(Box.createVerticalStrut(10));
        next.setPreferredSize(new Dimension(80, 40));
        next.setText("NEXT");
        JPanel panel1 = new JPanel();
        panel1.setBackground(Variables.getColorOfPanelWithCodonsModifications());
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
        panelWithNavigationButton.setBackground(Variables.getColorOfPanelWithCodonsModifications());
    }


    private void setPanelRight() {
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        setNormalSize(panelRight, new Dimension(400, this.getHeight()));

        setPanelRightTop();
        setPanelRightBottom();
        panelRight.add(panelRightTop);
        panelRight.add(Box.createVerticalStrut(100));
        panelRight.add(panelRightBottom);
        panelRight.setBackground(Variables.getColorOfRightPanel());

        splitPane.setRightComponent(panelRight);
    }

    private void setPanelRightTop() {
        Font leftLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        Font rightLabel = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        panelRightTop.setLayout(new BoxLayout(panelRightTop, BoxLayout.Y_AXIS));
        setNormalSize(panelRightTop, new Dimension(rightPanelWidth, 200));
        panelRightTop.setBackground(Variables.getColorOfRightPanel());

        JPanel panel1 = new MyTmpPanel();
        JPanel panel2 = new MyTmpPanel();
        JPanel panel4 = new MyTmpPanel();
        JPanel panel3 = new MyTmpPanel();
        JPanel[] panels = {panel1, panel2, panel3, panel4};
        setNormalSize(panels, new Dimension(rightPanelWidth, 50));

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
        panelRightTop.add(Box.createVerticalGlue());
        panelRightTop.add(panel4);
        panelRightTop.add(panel3);


    }

    private void setPanelRightBottom() {
        JPanel panel1 = new JPanel();
        int hGap = 100;
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, hGap, 0));
        int AACWidth = 60;
        ac1 = new JAminoAcidChooser(new Dimension(AACWidth, 20));
        ac2 = new JAminoAcidChooser(new Dimension(AACWidth, 20));
        setNormalSize(panel1, new Dimension(panelRightTop.getWidth(), 50));
        panel1.setBackground(Variables.getColorOfRightPanel());
        panel1.add(ac1.getRoot());
        panel1.add(ac2.getRoot());
        class CodonPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (ac1.getAminoAcid() != ac2.getAminoAcid()) {
                    int x = hGap + AACWidth / 2 - 24;
                    Color[] colors = {new Color(100, 200, 150), new Color(230, 200, 150), new Color(10, 130, 5), new Color(200, 10, 150), new Color(100, 200, 150)};
                    Graphics2D g2d = (Graphics2D) g;
                    for (int i = 0; i < ac1.getAminoAcid().getCodons().length; i++) {
                        g2d.drawString(ac1.getAminoAcid().getCodons()[i], x, 10 + 20 * (i));
                    }
                    for (int i = 0; i < ac2.getAminoAcid().getCodons().length; i++) {
                        g2d.drawString(ac2.getAminoAcid().getCodons()[i], this.getWidth() - x - 24, 10 + 20 * i);
                    }
                    int count = 0;
                    for (int i = 0; i < ac1.getAminoAcid().getCodons().length; i++) {
                        for (int j = 0; j < ac2.getAminoAcid().getCodons().length; j++) {
                            if (CreatingCodons.isSMP(ac1.getAminoAcid().getCodons()[i], ac2.getAminoAcid().getCodons()[j])) {
                                g2d.setColor(colors[count]);
                                count = (count + 1) % colors.length;
                                g2d.drawLine(x + 24, 10 + 20 * (i - 1) + 15, this.getWidth() - x - 24, 10 + 20 * (j - 1) + 15);
                            }
                        }
                    }

                }
            }

        }
        CodonPanel cp = new CodonPanel();
        cp.setBackground(Variables.getColorOfRightPanel());
        ac1.addValueChangeListener(e ->
                cp.repaint()
        );
        ac2.addValueChangeListener(e ->
                cp.repaint()
        );
        setNormalSize(panelRightBottom, new Dimension(rightPanelWidth, 400));
        panelRightBottom.setLayout(new BorderLayout());
        panelRightBottom.add(panel1, BorderLayout.NORTH);
        panelRightBottom.add(cp, BorderLayout.CENTER);

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
            int k = i;
            label1.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scrollPane.getHorizontalScrollBar().setValue(5 + Variables.getButtonWidth() * k);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            label1.setText(tmp.getTitle());
            aminoSequence.add(label1);


            JButton button = new JButton();
            button.setText(tmp.getTitle());
            button.setName(Integer.toString(i));
            button.setPreferredSize(new Dimension(Variables.getButtonWidth(), 20));
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

    class MyTmpPanel extends JPanel {
        public MyTmpPanel() {
            super();
            this.setBackground(Variables.getColorOfRightPanel());
        }
    }

    public JButton getSuffix() {
        return suffix;
    }

    public JButton getPrefix() {
        return prefix;
    }

    public static void setNormalSize(JComponent component, Dimension d) {
        component.setMaximumSize(d);
        component.setMinimumSize(d);
        component.setPreferredSize(d);
    }

    public static void setNormalSize(JComponent[] comps, Dimension d) {
        for (JComponent comp : comps) {
            setNormalSize(comp, d);
        }
    }
}
