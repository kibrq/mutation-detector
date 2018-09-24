package Listeners.ActionListners.AminoButton;

import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Listeners.KeyListeners.CreatingModes;

import Model.Amino;
import Model.Line;
import Model.Modification;
import Model.MyPoint;
import Global.Variables;
import jdk.nashorn.api.tree.VariableTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CreatingCodons implements ActionListener {
    private GUI gui = Variables.getGui();
    private double ppm = gui.getInputPPM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Variables.getGui().getInputPPM().getText());
    private double mistake = Variables.getCurrentMistake();

    private void countMistake() {
        int index = Variables.numPrefix == -1 ? (Variables.numSuffix == -1 ? numInArr : Variables.numSuffix) : Variables.numPrefix;


        double mist = Variables.isPrefixSelect ? ppm * Variables.massesPrefix[index] / Math.pow(10, 6) : (ppm / Math.pow(10, 6)) * (Variables.massesPrefix[Variables.massesPrefix.length - 1] + Variables.massesPrefix[index]);


        mistake = mist;
        mistake = round(mistake, 2);
        Variables.setCurrentMistake(mistake);
        System.out.println("Mistake is " + (Variables.isSuffixSelect ? "suffix " : "prefix ") + mistake);

        gui.getCurrentMistake().setText("  Current mistake:" + " " + mistake + "Da");
        gui.getCurrentMistake().repaint();
        gui.getPanelTop().repaint();
        gui.getPanelTop().revalidate();
    }

    private boolean isMassDifference(Amino a1, Amino a2) {
        mistake = Variables.getCurrentMistake();
        double trulyDm = round(a2.getMass() - a1.getMass(), 5);

        return trulyDm >= (dm - mistake) && trulyDm <= (dm + mistake);
    }

    private double round(double a, int radix) {
        a *= Math.pow(10, radix);
        a = Math.round(a);
        a /= Math.pow(10, radix);
        return a;
    }

    private boolean isOneCodonChange(String o1, String o2) {
        boolean flag1 = o1.substring(0, 2).compareTo(o2.substring(0, 2)) == 0;
        boolean flag2 = o1.substring(1, 3).compareTo(o2.substring(1, 3)) == 0;
        boolean flag3 = o1.charAt(0) == o2.charAt(0) && o1.charAt(2) == o2.charAt(2);
        return (!flag1 || !flag2) && (flag1 || flag2 || flag3);

    }

    private ArrayList<Integer> y_s1 = new ArrayList<>();
    private ArrayList<Integer> y_s2 = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private int k = -1;
    private ArrayList<Amino> db = Variables.getDb().getDatabase();
    private double dm = gui.getInputDM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Variables.getGui().getInputDM().getText());

    private void setModificationPanel() {

        JPanel pan = Variables.getGui().getPanelUnderBottomRight();
        pan.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(pan.getBackground());
        JLabel la = new JLabel("Possible modifications: ");
        panel.setPreferredSize(new Dimension(pan.getWidth(), 30));
        panel.add(la);
        pan.add(panel);
        boolean flag = false;
        if (Variables.getTmpModifications().get(numInArr) != null) {
            for (Modification mod : Variables.getTmpModifications().get(numInArr)) {
                flag = true;
                JPanel panel1 = new JPanel();
                panel1.setBackground(pan.getBackground());
                JLabel la1 = new JLabel(mod.getName());
                panel1.setPreferredSize(new Dimension(pan.getWidth(), 30));
                panel1.add(la1);
                pan.add(panel1);
            }
        }
        if (!flag) {
            JPanel panel1 = new JPanel();
            panel1.setBackground(pan.getBackground());
            JLabel la1 = new JLabel("Empty");
            panel1.setPreferredSize(new Dimension(pan.getWidth(), 30));
            panel1.add(la1);
            pan.add(panel1);
        }
        pan.revalidate();
        pan.repaint();


    }

    private void massDiff() {

        Variables.setCurrentCandidate(0);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));
        panel.setPreferredSize(new Dimension(150, 430));
        JLabel lb1 = new JLabel(db.get(k).getTitle());
        panel.add(lb1);
        panel.setName(db.get(k).getTitle());
        panel.setBackground(gui.getPanelBottom().getBackground());
        int l = 26;
        for (String s : db.get(k).getCodons()) {
            y_s1.add(l);
            JLabel lb = new JLabel(s);
            lb.setName(s);
            panel.add(lb);
            l += 21;
        }
        y_s1.add(l);
        Variables.panel1 = panel;
        gui.getFirstAmino().add(Variables.panel1);
        gui.getFirstAmino().revalidate();

        ArrayList<Amino> candidates = new ArrayList<>();
        for (int i = 0; i < db.size(); i++) {
            if (isThisOneFit(db.get(k), db.get(i))) {
                candidates.add(db.get(i));
            }
        }
        if (candidates.isEmpty()) {
            JLabel emp = new JLabel("Empty");
            Variables.panel2 = null;
            gui.getSecondAmino().add(emp);
            gui.getSecondAmino().revalidate();
        } else {

            if (candidates.size() > 1) {
                JButton prev = new JButton("Prev");
                JButton next = new JButton("Next");

                prev.addActionListener((ActionEvent e) -> {
                    if (Variables.getCurrentCandidate() - 1 <= -1) {
                        Variables.setCurrentCandidate(candidates.size() - 1);
                    } else {
                        Variables.minusCur();
                    }
                    fillingInY2s(candidates);
                });
                prev.addKeyListener(new CreatingModes());
                next.addActionListener(e -> {
                    if (Variables.getCurrentCandidate() + 1 >= candidates.size()) {
                        Variables.setCurrentCandidate(0);
                    } else {
                        Variables.plusCur();
                    }
                    fillingInY2s(candidates);
                });
                next.addKeyListener(new CreatingModes());
                gui.getNavigationPanel().add(prev);
                gui.getNavigationPanel().add(next);
            }
            gui.getNavigationPanel().repaint();

            fillingInY2s(candidates);
        }


    }

    private void fillingInY2s(ArrayList<Amino> candidates) {
        y_s2.clear();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));
        panel.setPreferredSize(new Dimension(150, 430));
        JLabel lb1 = new JLabel(candidates.get(Variables.getCurrentCandidate()).getTitle());
        panel.add(lb1);
        panel.setName(candidates.get(Variables.getCurrentCandidate()).getTitle());
        panel.setBackground(gui.getPanelBottom().getBackground());
        int l = 26;
        for (String s : candidates.get(Variables.getCurrentCandidate()).getCodons()) {
            y_s2.add(l);
            JLabel lb = new JLabel(s);
            lb.setName(s);
            panel.add(lb);
            l += 21;
        }
        y_s2.add(l);
        Variables.panel2 = panel;
        gui.getSecondAmino().removeAll();
        gui.getSecondAmino().revalidate();
        gui.getSecondAmino().add(Variables.panel2);
        gui.getSecondAmino().repaint();
        gui.getPanelLines().removeAll();

        gui.getPanelLines().revalidate();
        fillInLines();

    }

    private void fillInLines() {

        lines.clear();

        int count1 = 0;


        for (int i = 1; i < Variables.panel1.getComponents().length; i++) {
            boolean flag = false;

            Component comp1 = Variables.panel1.getComponents()[i];
            for (int j = 1; j < Variables.panel2.getComponents().length; j++) {
                Component comp2 = Variables.panel2.getComponents()[j];
                if (isOneCodonChange(comp1.getName(), comp2.getName())) {
                    flag = true;

                    int y1;
                    int y2;
                    if (Variables.isMassDiff()) {
                        y1 = y_s1.get(i - 1);
                        y2 = y_s2.get(j - 1);
                    } else {
                        y1 = comp1.getY() == 0 ? y_s1.get(i - 1) : comp1.getY();
                        y2 = comp2.getY() == 0 ? y_s1.get(j - 1) : comp2.getY();
                    }
                    MyPoint a = new MyPoint(0, y1);
                    MyPoint b = new MyPoint(gui.getPanelLines().getWidth(), y2);
                    lines.add(new Line(a, b, colors[count1]));
                }
            }
            if (flag) {
                count1++;
            }
        }
        double dm;

        double firMass = 0;
        double secMass = 0;
        for (Amino aDb : db) {
            if (aDb.getTitle().compareTo(Variables.panel1.getName()) == 0) {
                firMass = aDb.getMass();
            }
            if (aDb.getTitle().compareTo(Variables.panel2.getName()) == 0) {
                secMass = aDb.getMass();
            }
        }
        dm = round(secMass - firMass, 5);

        double dm1 = dm;

        class DrawPanel extends JPanel {
            public void paint(Graphics g) {
                for (Line tmp : lines) {
                    g.setColor(tmp.getColor());

                    g.drawLine(tmp.getA().getX(), tmp.getA().getY() + 13, tmp.getB().getX(), tmp.getB().getY() + 13);
                }

                g.setColor(Color.BLACK);
                g.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
                String diff = "dm: " + Double.toString(dm1) + "Da";
                g.drawString(diff, 65, 20);
            }

        }

        gui.getPanelLines().add(new DrawPanel(), BorderLayout.CENTER);
        gui.getPanelLines().repaint();
        gui.getPanelLines().revalidate();
        gui.getFirstAmino().repaint();
        gui.getSecondAmino().repaint();
        gui.getPanelUnderBottom().repaint();

    }

    static private int numInArr = 0;
    private Color[] colors = {Color.RED, Color.BLACK, Color.BLUE, Color.WHITE, Color.CYAN, Color.MAGENTA};


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton tmp = (JButton) e.getSource();
        String key = tmp.getText();
        numInArr = Integer.parseInt(tmp.getName());


        for (int i = 0; i < db.size(); i++) {
            if (key.compareTo(db.get(i).getTitle()) == 0) {
                k = i;
            }
        }
        if (k == -1) {
            return;
        }
        if (Variables.isCompareMode()) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));
            panel.setPreferredSize(new Dimension(30, 430));
            JLabel lb1 = new JLabel(key);
            panel.add(lb1);
            panel.setName(key);
            panel.setBackground(gui.getPanelBottom().getBackground());
            int l = 26;
            for (String s : db.get(k).getCodons()) {
                y_s1.add(l);
                JLabel lb = new JLabel(s);
                lb.setName(s);
                panel.add(lb);
                l += 21;
            }
            y_s1.add(l);

            if (Variables.panel1 != null && Variables.panel2 != null) {
                if (Variables.panel1.getName().compareTo(panel.getName()) == 0) {
                    Variables.panel1.setVisible(false);
                    Variables.panel1 = null;
                    Variables.getGui().getFirstAmino().removeAll();
                } else if (Variables.panel2.getName().compareTo(panel.getName()) == 0) {
                    Variables.panel2.setVisible(false);
                    Variables.panel2 = null;
                    Variables.getGui().getSecondAmino().removeAll();
                } else {
                    Variables.panel1.setVisible(false);
                    Variables.panel1 = null;
                    Variables.getGui().getFirstAmino().removeAll();
                    Variables.panel2.setVisible(false);
                    Variables.panel2 = null;
                    Variables.getGui().getSecondAmino().removeAll();
                    Variables.panel1 = panel;
                    Variables.getGui().getFirstAmino().add(Variables.panel1);
                }
            } else if (Variables.panel1 == null && Variables.panel2 == null) {
                Variables.panel1 = panel;
                Variables.getGui().getFirstAmino().add(Variables.panel1);
            } else {
                if (Variables.panel1 == null) {
                    if (Variables.panel2.getName().compareTo(panel.getName()) == 0) {
                        Variables.panel2.setVisible(false);
                        Variables.panel2 = null;
                        Variables.getGui().getSecondAmino().removeAll();
                    } else {
                        Variables.panel1 = panel;
                        Variables.getGui().getFirstAmino().add(Variables.panel1);

                    }
                } else {
                    if (Variables.panel1.getName().compareTo(panel.getName()) == 0) {
                        Variables.panel1.setVisible(false);
                        Variables.panel1 = null;
                        Variables.getGui().getFirstAmino().removeAll();
                    } else {
                        Variables.panel2 = panel;
                        Variables.getGui().getSecondAmino().add(Variables.panel2);

                    }
                }
            }

            gui.getFirstAmino().revalidate();
            gui.getSecondAmino().revalidate();
            gui.getPanelLines().removeAll();
            if (Variables.panel1 != null && Variables.panel2 != null) {
                fillInLines();
            }
            gui.getFirstAmino().repaint();
            gui.getSecondAmino().repaint();
            gui.getPanelUnderBottom().repaint();
        } else if (Variables.isMassDiff()) {
            if (Variables.isPrefixSelect || Variables.isSuffixSelect) {
                PrefixSuffixSelect();
            } else {
                gui.getFirstAmino().removeAll();
                gui.getFirstAmino().repaint();
                gui.getSecondAmino().removeAll();
                gui.getSecondAmino().repaint();
                gui.getPanelLines().removeAll();
                gui.getPanelLines().repaint();
                gui.getNavigationPanel().removeAll();
                gui.getNavigationPanel().repaint();

                massDiff();
                setModificationPanel();
            }
        } else {
            if (Variables.panel1 != null) {
                Variables.panel1.setVisible(false);
                Variables.panel1 = null;
            }
            if (Variables.panel2 != null) {
                Variables.panel2.setVisible(false);
                Variables.panel2 = null;
            }
            JPopupMenu pop = new JPopupMenu();
            JPanel panel = new JPanel(new GridLayout(db.get(k).getCodons().length, 1, 0, 10));
            for (int i = 0; i < db.get(k).getCodons().length; i++) {
                JLabel label = new JLabel(db.get(k).getCodons()[i]);
                panel.add(label);
            }
            pop.add(panel);
            pop.addKeyListener(new CreatingModes());
            pop.show(gui.getPanelUnderBottom(), gui.getWidth() / 2 - 20, 0);

        }


    }

    private void PrefixSuffixSelect() {


        UsefullFunctions.removeAllFromUnderBottomPanel();
        UsefullFunctions.repaintPanelTop();
        UsefullFunctions.refreshPanelTop();

        countMistake();

        boolean flag = false;
        Variables.numSuffix = -1;
        Variables.numPrefix = -1;
        if (Variables.isPrefixSelect) {
            Variables.numPrefix = numInArr;
        } else {
            Variables.numSuffix = numInArr;
        }

        Variables.setTmpModifications(UsefullFunctions.findPossibleModification(Variables.isPrefixSelect));

        HashMap<Integer, ArrayList<Modification>> hm = Variables.getTmpModifications();
        System.out.println(hm);

        int beginIndex = Variables.isPrefixSelect ? 0 : Variables.numSuffix;
        int endIndex = Variables.isPrefixSelect ? Variables.numPrefix + 1 : Variables.seq.length;
        for (int i = beginIndex; i < endIndex; i++) {
            boolean tmp = false;
            if (!hm.get(i).isEmpty()) {
                flag = true;
                tmp = true;
                gui.getPanelTop().getComponent(i).setForeground(Variables.colorOfChoosenByModification);
                gui.getAminoPanel().getComponent(i).setForeground(Variables.colorOfChoosenByModification);
            }
            Amino acid = Variables.seq[i];
            for (Amino aDb : db) {
                if (isThisOneFit(acid, aDb)) {

                    flag = true;
                    Color col = Variables.colorOfChoosenByChange;
                    if (tmp) {
                        col = Variables.colorOfChoosen;
                    }
                    gui.getPanelTop().getComponent(i).setForeground(col);
                    gui.getAminoPanel().getComponent(i).setForeground(col);
                }
            }

        }

        gui.getPanelTop().repaint();
        gui.getPanelTop().revalidate();
        gui.getAminoPanel().revalidate();
        gui.getAminoPanel().repaint();
        if (!flag) {
            JPopupMenu popupMenu = new JPopupMenu();
            String s = Variables.isPrefixSelect ? "prefix" : "suffix";
            popupMenu.add(new JLabel("There is no amino acids with such mass difference in this" + " " + s));
            popupMenu.show(gui.getBut(), -20, 30);
        }
        UsefullFunctions.underlineAminos(Variables.isPrefixSelect ? endIndex : 0, Variables.isPrefixSelect ? Variables.seq.length : beginIndex);

        Variables.isSuffixSelect = false;
        Variables.isPrefixSelect = false;

    }

    private boolean isOneCodonChanges(Amino a1, Amino a2) {
        String[] cod1 = a1.getCodons();
        String[] cod2 = a2.getCodons();
        for (int i = 0; i < cod1.length; i++) {
            for (int j = 0; j < cod2.length; j++) {
                if (isOneCodonChange(cod1[i], cod2[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isThisOneFit(Amino a1, Amino a2) {
        return isOneCodonChanges(a1, a2) && isMassDifference(a1, a2) && (!a1.equals(a2));
    }
}
