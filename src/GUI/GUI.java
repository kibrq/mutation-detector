package GUI;


import AdditionalClasses.UsefullFunctions;
import Global.*;
import Listeners.ActionListners.AminoButton.AminoAcidClicked;
import Listeners.KeyListeners.ChangePPM;
import Listeners.KeyListeners.Scrolling;
import Model.AminoAcid;
import Model.Mode;
import MyComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


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
    private JButton suffix = new JButton("Spot suffix");
    private JButton prefix = new JButton("Spot prefix");


    private JPanel innerPane = new JPanel();
    private JScrollPane scrollPaneWithAminoAcids;

    private JPanel panelRight = new JPanel();

    private JPanel panelRightTop = new JPanel();
    private JLabel massDifference = new JLabel();
    private JTextField mistakePPM = new JTextField();
    private JButton changePPM = new JButton();
    private JLabel currentMode = new JLabel();
    private JButton changeMode = new JButton("Set");
    private JLabel currentMistake = new JLabel();

    private JPanel panelRightBottom = new JPanel();


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

        panelWithScrollPane.setBackground(Variables.getColorOfPanelWithSequnece());
        panelLeftCenter.add(panelWithScrollPane, BorderLayout.NORTH);
        innerPane.setLayout(new BoxLayout(innerPane, BoxLayout.Y_AXIS));

        scrollPaneWithAminoAcids = new JScrollPane(innerPane);
        scrollPaneWithAminoAcids.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneWithAminoAcids.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelLeftCenter.add(scrollPaneWithAminoAcids, BorderLayout.CENTER);

        panelLeftCenter.setBackground(Variables.getColorOfPanelWithCodonsModifications());


    }

    public void addAminoAcid(ArrayList<ArrayList<Integer>> a) {
        for (int i = 0; i < a.size(); i++) {
            JPanel panel = new JPanel();
            UsefullFunctions.setNormalSize(panel, new Dimension(innerPane.getWidth(), 600));
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 0);
            panel2.setLayout(flowLayout);
            panel3.setLayout(flowLayout);
            JPanel panels[] = {panel2, panel3};
            UsefullFunctions.setNormalSize(panels, new Dimension(500, 35));
            GridLayout gridLayout = new GridLayout();
            gridLayout.setColumns(1);
            gridLayout.setRows(2);
            panel1.setLayout(gridLayout);
            JLabel label = new JLabel();
            JLabel label1 = new JLabel();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            label.setFont(Variables.getFontForAminoTitles());
            if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
                label.setForeground(UsefullFunctions.spotHiglightedAminoAcids(a.get(i)));
            }
            sb.append(Variables.getSeq()[a.get(i).get(0)].getTitle());
            sb.append(" (");
            sb.append(Variables.getSeq()[a.get(i).get(0)].getFullName());
            sb.append(") ");
            if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
                sb.append(a.get(i).size() == 1 ? "at position: " : "at positions: ");

                for (int j = 0; j < a.get(i).size() - 1; j++) {
                    sb.append(a.get(i).get(j) + 1);
                    sb.append(", ");
                }
                sb.append(a.get(i).get(a.get(i).size() - 1) + 1);

                sb1.append(" Maximum mistake is ");
                sb1.append(Variables.countMistakeByNum(a.get(i).get(a.get(i).size() - 1)));
                sb1.append("Da");

                sb1.append("; Minimum mistake is ");
                sb1.append(Variables.countMistakeByNum(a.get(i).get(0)));
                sb1.append("Da");
            }
            label.setText(sb.toString());
            label1.setText(sb1.toString());

            panel2.add(label);
            panel3.add(label1);

            panel1.add(panel2);
            panel1.add(panel3);


            JSplitPane sp = new JSplitPane();
            JPanel panelLeft = new JSubstitutionsPanel(a.get(i).get(0));
            JPanel panelRight = new JModificationPanel(a.get(i).get(0));
            int m = 3;
            int k = 3;
            UsefullFunctions.setNormalSize(panelLeft, new Dimension(m*innerPane.getWidth() / (m+k), 500));
            UsefullFunctions.setNormalSize(panelRight, new Dimension(k*innerPane.getWidth() / (m+k), 500));
            sp.setLeftComponent(panelLeft);
            sp.setRightComponent(panelRight);
            panel.add(panel1, BorderLayout.NORTH);
            panel.add(sp, BorderLayout.CENTER);
            innerPane.add(Box.createVerticalStrut(20));
            innerPane.add(panel);
        }
        scrollPaneWithAminoAcids.repaint();
        scrollPaneWithAminoAcids.revalidate();
    }

    public void removeAllAminoAcids() {
        innerPane.removeAll();
        scrollPaneWithAminoAcids.revalidate();
        UsefullFunctions.clearBackgrounds();
    }

    private void setPanelRight() {
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
        UsefullFunctions.setNormalSize(panelRight, new Dimension(400, this.getHeight()));

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
        UsefullFunctions.setNormalSize(panelRightTop, new Dimension(rightPanelWidth, 200));
        panelRightTop.setBackground(Variables.getColorOfRightPanel());

        JPanel panel1 = new MyTmpPanel();
        JPanel panel2 = new MyTmpPanel();
        JPanel panel4 = new MyTmpPanel();
        JPanel panel3 = new MyTmpPanel();
        JPanel[] panels = {panel1, panel2, panel3, panel4};
        UsefullFunctions.setNormalSize(panels, new Dimension(rightPanelWidth, 50));

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label1 = new JLabel("Mass Difference: ");
        label1.setFont(leftLabel);
        massDifference.setFont(rightLabel);
        massDifference.setText("23.04");
        panel1.add(label1);
        panel1.add(massDifference);

        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel label2 = new JLabel("Error tolerance: ");
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
        changePPM.setText("Set");
        changePPM.setName("Change");
        changePPM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton tmp = (JButton) e.getSource();
                if (tmp.getText().equals("Set")) {
                    mistakePPM.setFocusable(true);
                    mistakePPM.grabFocus();
                    tmp.setText("Submit");

                } else {
                    tmp.setText("Set");
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
        label4.addMouseListener(new MouseListener() {
            JPopupMenu popm;

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                popm = new JPopupMenu();
                popm.add(new JLabel("You can change the mode by hot keys(shift + f's) or by button 'set mode'. "));
                popm.show(label4, -20, 20);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popm.setVisible(false);
            }
        });
        label4.setFont(leftLabel);
        currentMode.setFont(rightLabel);
        currentMode.setText("Viewing of codons");
        panel4.add(label4);
        panel4.add(currentMode);
        changeMode.addActionListener(e -> {
            Mode.next();
        });
        panel4.add(changeMode);


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
        int hGap = 100;
        int AACWidth = 60;
        JHintsOnSubs hints = new JHintsOnSubs(hGap, AACWidth);
        UsefullFunctions.setNormalSize(hints, new Dimension(rightPanelWidth, 300));
        UsefullFunctions.setNormalSize(panelRightBottom, new Dimension(rightPanelWidth, 400));

        panelRightBottom.add(hints, BorderLayout.CENTER);
        panelRightBottom.setBackground(Variables.getColorOfRightPanel());

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
                arr[i] = tmp.getMass() + arr[i - 1];
            }
            JPanel panel = new JPanel();
            panel.setBackground(Variables.getColorOfPanelWithSequnece());
            panel.setLayout(new BorderLayout());
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
            panel.add(label1);
            aminoSequence.add(panel);


            JButton button = new JAminoButton();
            button.setText(tmp.getTitle());
            button.setName(Integer.toString(i));
            button.setPreferredSize(new Dimension(Variables.getButtonWidth(), 20));
            button.addActionListener(new AminoAcidClicked());
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


}
