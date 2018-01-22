package ActionListeners;

import GUI.GUI;
import Model.Amino;
import Model.Line;
import Model.MyPoint;
import Start.Start;

import javax.swing.*;
import javax.xml.stream.events.StartDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CreatingCodons implements ActionListener {
    private GUI gui = Start.getGui();
    private double ppm = gui.getInputPPM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Start.getGui().getInputPPM().getText());

    private boolean isMassDifference(Amino a1, Amino a2, double dm, double ppm) {
        double trulyDm = round(Math.abs(a1.getMass() - a2.getMass()), 5);
        double mist = ppm / Math.pow(10, 6);
        return trulyDm >= (dm - mist) && trulyDm <= (dm + mist);
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
        if (flag1 && flag2) return false;
        return flag1 || flag2 || flag3;

    }

    private ArrayList<Integer> y_s1 = new ArrayList<>();
    private ArrayList<Integer> y_s2 = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private int k = -1;
    private ArrayList<Amino> db = Start.getDb().getDatabase();

    private void massDiff() {
        Start.setCurrentCandidate(0);
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
        Start.panel1 = panel;
        gui.getFirstAmino().add(Start.panel1);
        gui.getFirstAmino().revalidate();

        ArrayList<Amino> candidates = new ArrayList<>();
        double dm = gui.getInputDM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Start.getGui().getInputDM().getText());
        for (Amino aDb : db) {
            if (isMassDifference(aDb, db.get(k), dm, ppm)) {
                candidates.add(aDb);
            }
        }
        if (candidates.isEmpty()) {
            JLabel emp = new JLabel("Empty");
            Start.panel2 = null;
            gui.getSecondAmino().add(emp);
            gui.getSecondAmino().revalidate();
        } else {

            if (candidates.size() > 1) {
                JButton prev = new JButton("Prev");
                JButton next = new JButton("Next");

                prev.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Start.getCurrentCandidate() - 1 <= -1) {
                            Start.setCurrentCandidate(candidates.size() - 1);
                        } else {
                            Start.minusCur();
                        }
                        fillingInY2s(candidates);
                    }
                });
                next.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Start.getCurrentCandidate() + 1 >= candidates.size()) {
                            Start.setCurrentCandidate(0);
                        } else {
                            Start.plusCur();
                        }
                        fillingInY2s(candidates);
                    }
                });
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
        JLabel lb1 = new JLabel(candidates.get(Start.getCurrentCandidate()).getTitle());
        panel.add(lb1);
        panel.setName(candidates.get(Start.getCurrentCandidate()).getTitle());
        panel.setBackground(gui.getPanelBottom().getBackground());
        int l = 26;
        for (String s : candidates.get(Start.getCurrentCandidate()).getCodons()) {
            y_s2.add(l);
            JLabel lb = new JLabel(s);
            lb.setName(s);
            panel.add(lb);
            l += 21;
        }
        y_s2.add(l);
        Start.panel2 = panel;
        gui.getSecondAmino().removeAll();
        gui.getSecondAmino().revalidate();
        gui.getSecondAmino().add(Start.panel2);
        gui.getSecondAmino().repaint();
        gui.getPanelLines().removeAll();

        gui.getPanelLines().revalidate();
        fillInLines();

    }

    private void fillInLines() {

        lines.clear();

        int count1 = 0;


        for (int i = 1; i < Start.panel1.getComponents().length; i++) {
            boolean flag = false;

            Component comp1 = Start.panel1.getComponents()[i];
            for (int j = 1; j < Start.panel2.getComponents().length; j++) {
                Component comp2 = Start.panel2.getComponents()[j];
                if (isOneCodonChange(comp1.getName(), comp2.getName())) {
                    flag = true;

                    int y1;
                    int y2;
                    if (Start.isMassDiff()) {
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
        double dm = 0;

        double firMass = 0;
        double secMass = 0;
        for (Amino aDb : db) {
            if (aDb.getTitle().compareTo(Start.panel1.getName()) == 0) {
                firMass = aDb.getMass();
            }
            if (aDb.getTitle().compareTo(Start.panel2.getName()) == 0) {
                secMass = aDb.getMass();
            }
        }
        dm = round(Math.abs(firMass - secMass), 5);

        double dm1 = dm;

        class DrawPanel extends JPanel {
            public void paint(Graphics g) {
                for (Line tmp : lines) {
                    g.setColor(tmp.getColor());
                    g.drawLine(tmp.getA().getX(), tmp.getA().getY() + 13, tmp.getB().getX(), tmp.getB().getY() + 13);
                }

                g.setColor(Color.BLACK);
                g.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
                String diff = "dm: " + Double.toString(dm1) + "d";
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


    private Color[] colors = {Color.RED, Color.BLACK, Color.BLUE, Color.WHITE, Color.CYAN, Color.MAGENTA};

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton tmp = (JButton) e.getSource();
        String key = tmp.getName();

        for (int i = 0; i < db.size(); i++) {
            if (key.compareTo(db.get(i).getTitle()) == 0) {
                k = i;
            }
        }
        if (k == -1) {
            return;
        }
        if (Start.isCompareMode()) {
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

            if (Start.panel1 == null) {
                if (Start.panel2 != null && Start.panel2.getName().compareTo(panel.getName()) == 0) {
                    Start.panel2.setVisible(false);
                    Start.panel2 = null;
                } else {
                    Start.panel1 = panel;
                    gui.getFirstAmino().add(Start.panel1);
                    Start.setPreviousList(2);

                }
            } else if (Start.panel2 == null) {
                if (Start.panel1.getName().compareTo(panel.getName()) != 0) {
                    Start.panel2 = panel;
                    Start.setPreviousList(1);
                    gui.getSecondAmino().add(Start.panel2);
                } else {
                    Start.panel1.setVisible(false);
                    Start.panel1 = null;
                }
            } else if (Start.panel1.getName().compareTo(panel.getName()) == 0) {
                Start.panel1.setVisible(false);
                Start.panel1 = null;
            } else if (Start.panel2.getName().compareTo(panel.getName()) == 0) {
                Start.panel2.setVisible(false);
                Start.panel2 = null;
            } else {
                if (Start.getPreviousList() == 1) {
                    Start.panel1.setVisible(false);
                    Start.panel1 = panel;
                    gui.getFirstAmino().add(Start.panel1);
                    Start.setPreviousList(2);

                } else {
                    Start.panel2.setVisible(false);
                    Start.panel2 = panel;
                    gui.getSecondAmino().add(Start.panel2);
                    Start.setPreviousList(1);
                }
            }

            gui.getFirstAmino().revalidate();
            gui.getSecondAmino().revalidate();
            gui.getPanelLines().removeAll();
            if (Start.panel1 != null && Start.panel2 != null) {
                fillInLines();
            }
            gui.getFirstAmino().repaint();
            gui.getSecondAmino().repaint();
            gui.getPanelUnderBottom().repaint();
        } else if (Start.isMassDiff()) {
            gui.getFirstAmino().removeAll();
            gui.getFirstAmino().repaint();
            gui.getSecondAmino().removeAll();
            gui.getSecondAmino().repaint();
            gui.getPanelLines().removeAll();
            gui.getPanelLines().repaint();
            gui.getNavigationPanel().removeAll();
            gui.getNavigationPanel().repaint();

            massDiff();
        } else {
            if (Start.panel1 != null) {
                Start.panel1.setVisible(false);
                Start.panel1 = null;
            }
            if (Start.panel2 != null) {
                Start.panel2.setVisible(false);
                Start.panel2 = null;
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
}
