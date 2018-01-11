package ActionListeners;

import Database.DataBase;
import GUI.GUI;
import Model.Line;
import Model.MyPoint;
import Start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatingCodons implements ActionListener {

    private double round(double a) {
        a *= 10000;
        a = Math.round(a);
        return a /= 10000;
    }

    private boolean isOneCodonChange(String o1, String o2) {
        boolean flag1 = o1.substring(0, 2).compareTo(o2.substring(0, 2)) == 0;
        boolean flag2 = o1.substring(1, 3).compareTo(o2.substring(1, 3)) == 0;
        boolean flag3 = o1.charAt(0) == o2.charAt(0) && o1.charAt(2) == o2.charAt(2);
        if (flag1 && flag2) return false;
        return flag1 || flag2 || flag3;

    }

    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.CYAN, Color.MAGENTA};

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI gui = Start.getGui();
        JButton tmp = (JButton) e.getSource();
        String key = tmp.getName();

        DataBase db = Start.getDb();
        int k = -1;
        for (int i = 0; i < db.getDatabase().size(); i++) {
            if (key.compareTo(db.getDatabase().get(i).getTitle()) == 0) {
                k = i;
            }
        }
        if (k == -1) {
            return;
        }
        if (Start.isCompareMode()) {
            JPanel panel = new JPanel(new FlowLayout(1, 100, 5));
            panel.setPreferredSize(new Dimension(150, 430));
            JLabel lb1 = new JLabel(key);
            panel.add(lb1);
            panel.setName(key);
            panel.setBackground(gui.getPanelBottom().getBackground());
            ArrayList<Integer> y_s = new ArrayList<>();
            int l = 26;
            for (String s : db.getDatabase().get(k).getCodons()) {
                y_s.add(l);
                JLabel lb = new JLabel(s);
                lb.setName(s);
                panel.add(lb);
                l += 21;
            }
            y_s.add(l);

            if (Start.panel1 == null) {
                if (Start.panel2 != null && Start.panel2.getName().compareTo(panel.getName()) == 0) {
                    Start.panel2.setVisible(false);
                    Start.panel2 = null;
                }
                Start.panel1 = panel;
                gui.getFirstAmino().add(Start.panel1);
                Start.setPreviousList(2);
                gui.getFirstAmino().revalidate();

            } else if (Start.panel2 == null) {
                if (Start.panel1.getName().compareTo(panel.getName()) != 0) {
                    Start.panel2 = panel;
                    Start.setPreviousList(1);
                    gui.getSecondAmino().add(Start.panel2);
                    gui.getSecondAmino().revalidate();

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
                    gui.getFirstAmino().revalidate();
                } else {
                    Start.panel2.setVisible(false);
                    Start.panel2 = panel;
                    gui.getSecondAmino().add(Start.panel2);
                    Start.setPreviousList(1);
                    gui.getSecondAmino().revalidate();
                }
            }

            gui.getPanelLines().removeAll();
            ArrayList<Line> al = new ArrayList<>();
            if (Start.panel1 != null && Start.panel2 != null) {

                if (Start.panel1.getName().compareTo(Start.panel2.getName()) != 0) {
                    int count1 = 0;

                    for (int i = 1; i < Start.panel1.getComponents().length; i++) {
                        boolean flag = false;

                        Component comp1 = Start.panel1.getComponents()[i];
                        for (int j = 1; j < Start.panel2.getComponents().length; j++) {
                            Component comp2 = Start.panel2.getComponents()[j];
                            if (isOneCodonChange(comp1.getName(), comp2.getName())) {
                                flag = true;
                                int y1 = comp1.getY() == 0 ? y_s.get(i - 1) : comp1.getY();
                                int y2 = comp2.getY() == 0 ? y_s.get(j - 1) : comp2.getY();
                                MyPoint a = new MyPoint(0, y1);
                                MyPoint b = new MyPoint(100, y2);
                                al.add(new Line(a, b, colors[count1]));
                            }
                        }
                        if (flag) {
                            count1++;
                        }

                    }
                }


                double firMass = 0;
                double secMass = 0;
                for (int i = 0; i < db.getDatabase().size(); i++) {
                    if (db.getDatabase().get(i).getTitle().compareTo(Start.panel1.getName()) == 0) {
                        firMass = db.getDatabase().get(i).getMass();
                    }
                    if (db.getDatabase().get(i).getTitle().compareTo(Start.panel2.getName()) == 0) {
                        secMass = db.getDatabase().get(i).getMass();
                    }
                }
                double dm = round(Math.abs(firMass - secMass));

                class DrawPanel extends JPanel {
                    public void paint(Graphics g) {
                        g.setColor(Color.BLACK);
                        g.setFont(Font.getFont(Font.SERIF));
                        g.drawString("dm : " + Double.toString(dm)+"g", 20, 20);
                        for (Line tmp : al) {
                            g.setColor(tmp.getColor());
                            g.drawLine(tmp.getA().getX(), tmp.getA().getY() + 13, tmp.getB().getX(), tmp.getB().getY() + 13);
                        }
                    }
                }
                gui.getPanelLines().add(new DrawPanel(), BorderLayout.CENTER);
                gui.getPanelLines().repaint();
                gui.getPanelLines().revalidate();
            }
            gui.getFirstAmino().repaint();
            gui.getSecondAmino().repaint();
            gui.getPanelUnderBottom().repaint();
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
            JPanel panel = new JPanel(new GridLayout(db.getDatabase().get(k).getCodons().length, 1, 0, 10));
            for (int i = 0; i < db.getDatabase().get(k).getCodons().length; i++) {
                JLabel label = new JLabel(db.getDatabase().get(k).getCodons()[i]);
                panel.add(label);
            }
            pop.add(panel);
            pop.addKeyListener(new CreatingModes());
            pop.show(gui.getPanelUnderBottom(), 430, 0);

        }


    }
}

