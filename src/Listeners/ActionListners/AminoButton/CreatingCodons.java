package Listeners.ActionListners.AminoButton;

import AdditionalClasses.UsefullFunctions;

import Global.Variables;
import Model.AminoAcid;
import Model.Mode;
import Model.Modification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CreatingCodons implements ActionListener {
    int i = 0;
    double ppm = 0;
    double dm = 0;

    class PaintPanel extends JPanel {
        Color[] colors = {new Color(200, 0, 0), new Color(0, 200, 0), new Color(0, 0, 200), new Color(100, 0, 0), new Color(0, 100, 0), new Color(0, 0, 100), new Color(100, 20, 100), new Color(20, 100, 100)};

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            int x = this.getWidth() / 6;
            int y = 65;
            Graphics2D g2d = (Graphics2D) g;

            if (Variables.getMode() != Mode.NORMAL) {
                g2d.setFont(Variables.getFontForTitles());
                g2d.drawString("Substitutions", this.getWidth() / 2 - 68, 20);
                StringBuilder s = new StringBuilder();
                if (Variables.getA1() != null) {
                    s.append(Variables.getA1().getTitle());
                    s.append("(");
                    boolean first = true;
                    for (int i = 0; i < Variables.getSeq().length; i++) {
                        if (Variables.getSeq()[i] == Variables.getA1()) {
                            if (!first) {
                                s.append(",");
                            }
                            s.append(Integer.toString(i + 1));
                            first = false;
                        }
                    }
                    s.append(")");
                    g2d.drawString(s.toString(), x, y);
                    g2d.setFont(Variables.getFontForLittleSignings());
                    int y1 = y + 60;
                    for (String s1 : Variables.getA1().getCodons()) {
                        g2d.drawString(s1, x - 11, y1);
                        y1 += 30;
                    }
                    g2d.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
                    g2d.drawString("Current mistake is " + Double.toString(countTMPMistake(i)) + "Da", x * 3 - 35 * 3 + 5, y + 350);


                }
                g2d.setFont(Variables.getFontForTitles());

                if (Variables.getA2() != null) {
                    g2d.drawString(Variables.getA2().getTitle(), this.getWidth() - x - 30, y);
                    g2d.setFont(Variables.getFontForLittleSignings());
                    int y1 = y + 60;
                    for (String s1 : Variables.getA2().getCodons()) {
                        g2d.drawString(s1, this.getWidth() - x - 41, y1);
                        y1 += 30;
                    }
                } else if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
                    g2d.drawString("NONE", this.getWidth() - x - 50, y);
                }
                if (Variables.getA1() != null && Variables.getA2() != null) {
                    g2d.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
                    double dm1 = UsefullFunctions.round(Variables.getA1().getMass() - Variables.getA2().getMass());
                    g2d.drawString("Difference in mass is " + Double.toString(dm1) + "Da", x * 3 - 35 * 3 + 5, y + 300);
                    int color = 0;
                    int i = 0;
                    for (String s1 : Variables.getA1().getCodons()) {
                        int j = 0;
                        for (String s2 : Variables.getA2().getCodons()) {
                            if (isSMP(s1, s2)) {
                                g2d.setColor(colors[color]);
                                g2d.drawLine(x + 3 * 10 + 4, y + 60 + 30 * i - 8, this.getWidth() - x - 44, y + 60 + 30 * j - 8);
                                color++;
                            }
                            j++;
                        }
                        i++;
                    }
                }

            } else {
                g2d.setFont(Variables.getFontForTitles());
                String s = "Encoding codons for " + Variables.getCurrentAmino().getFullName() + ":";
                g2d.drawString(s, this.getWidth() / 2 - s.length() * 10 / 2, 20);

                x = this.getWidth() / 2 - s.length() * 10 / 2;
                int l = s.length() * 8;
                g2d.setFont(Variables.getFontForLittleSignings());
                int y1 = y + 20;
                for (String s1 : Variables.getA1().getCodons()) {
                    g2d.drawString(s1, x + l / 2 + 24, y1);
                    y1 += 30;
                }
            }
        }
    }

    class ModificationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            int x = 128;
            int y = 65;
            super.paintComponent(g);
            Graphics2D q = (Graphics2D) g;
            q.setFont(Variables.getFontForTitles());
            q.drawString("Possible modifications", this.getWidth() / 2 - x, y);
            y += 60;
            q.setFont(Variables.getFontForLittleSignings());
            int j = 1;
            boolean flag = false;
            for (Modification mod : Variables.getMode() == Mode.MASS_DIFFERENCE ? Variables.getTmpModifications().get(i) : Variables.getModificationDataBase().get(Variables.getCurrentAmino())) {
                q.drawString(j + ". " + mod.getName() + " (" + mod.getMassDifference() + "Da)", this.getWidth() / 2 - x, y);
                y += 30;
                flag = true;
                j++;
            }
            if (!flag) {
                q.drawString("NONE", this.getWidth() / 2 - 10, y);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dm = Variables.getDm();
        ppm = Variables.getPpm();
        Variables.getGui().getPanelWithCodons().removeAll();
        Variables.getGui().getPanelWithModifications().removeAll();
        String s = ((JButton) e.getSource()).getText();
        i = Integer.parseInt(((JButton) e.getSource()).getName());
        System.out.println(i);
        if (Variables.isPrefixSelecting() || Variables.isSuffixSelecting()) {
            paintAminoSequence(i);

        } else {

            AminoAcid a = AminoAcid.valueOf(s);
            Variables.setCurrentAmino(a);
            Variables.setCurrentCandidate(0);

            switch (Variables.getMode()) {
                case NORMAL:
                    Variables.setA1(a);
                    break;
                case MASS_DIFFERENCE:
                    Variables.setA1(a);
                    Variables.getGui().getNext().setVisible(false);
                    if (Variables.getCandidates() != null && !Variables.getCandidates().get(i).isEmpty()) {
                        Variables.setA2(Variables.getCandidates().get(i).get(Variables.getCurrentCandidate()));
                        if (Variables.getCandidates().get(i).size() > 1) {
                            Variables.getGui().getNext().setVisible(true);
                            Variables.getGui().getPanelWithNavigationButton().repaint();
                        }
                    } else {
                        System.out.println(Variables.getCandidates().get(i));
                        Variables.setA2(null);
                    }
                    break;

            }


            JPanel panel2 = new ModificationPanel();
            panel2.setMinimumSize(new Dimension(300, 200));
            panel2.setBackground(Variables.getColorOfPanelWithCodonsModifications());
            Variables.getGui().getPanelWithModifications().add(panel2, BorderLayout.CENTER);

            UsefullFunctions.revalidateRepaint(Variables.getGui().getPanelWithModifications());
            JPanel panel1 = new PaintPanel();
            panel1.setBackground(Variables.getColorOfPanelWithCodonsModifications());
            Variables.getGui().getPanelWithCodons().removeAll();
            Variables.getGui().getPanelWithCodons().add(panel1, BorderLayout.CENTER);
            UsefullFunctions.revalidateRepaint(Variables.getGui().getPanelWithCodons());

        }
    }

    private void countMistake() {
        System.out.println(i);
        Variables.setCurrentMistake(Variables.isPrefixSelecting() ? ppm * Variables.getMassesPrefix()[i] / Math.pow(10, 6) : ppm * (Variables.getMassesPrefix()[Variables.getMassesPrefix().length - 1] + Variables.getMassesPrefix()[i]) / Math.pow(10, 6));
    }

    private double countTMPMistake(int num) {
        return UsefullFunctions.round(Variables.isPrefixSelected() ? ppm * Variables.getMassesPrefix()[num] / Math.pow(10, 6) : ppm * (Variables.getMassesPrefix()[Variables.getMassesPrefix().length - 1] + Variables.getMassesPrefix()[num]) / Math.pow(10, 6));

    }

    private void paintAminoSequence(int num) {
        Variables.setPrefixSelected(false);
        Variables.setCandidates(new HashMap<>());
        Variables.setTmpModifications(new HashMap<>());
        countMistake();
        int start = 0;
        int end = 0;
        if (Variables.isPrefixSelecting()) {
            start = 0;
            end = num;
            Variables.setPrefixSelected(true);
        } else {
            Variables.setPrefixSelected(false);
            start = num;
            end = Variables.getSeq().length;
        }
        boolean pref = Variables.isPrefixSelecting();
        Variables.setPrefixSelecting(false);
        Variables.setSuffixSelecting(false);
        boolean flah = false;
        for (int i = 0; i < Variables.getSeq().length; i++) {
            Variables.getGui().getPanelWithAminoButtons().getComponent(i).setEnabled(false);
            if (i >= start && i < end) {
                findCandidatesForThisAmino(Variables.getSeq()[i], i);
                if (!Variables.getCandidates().get(i).isEmpty()) {
                    flah = true;
                    Variables.getGui().getAminoSequence().getComponent(i).setForeground(Variables.getColorOfMut());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfMut());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setEnabled(true);
                }
                findModifications(Variables.getSeq()[i], i);
                if (!Variables.getTmpModifications().get(i).isEmpty()) {
                    flah = true;
                    Variables.getTmpModifications().get(i).sort(new Comparator<Modification>() {
                        @Override
                        public int compare(Modification o1, Modification o2) {
                            if (o1.getMassDifference() != o2.getMassDifference()) {
                                return Math.abs(o1.getMassDifference() - dm) - Math.abs(o2.getMassDifference() - dm) > 0 ? 1 : -1;
                            }
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    if (Variables.getGui().getAminoSequence().getComponent(i).getForeground() == Variables.getColorOfMut()) {
                        Variables.getGui().getAminoSequence().getComponent(i).setForeground(Variables.getColorOfBoth());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfBoth());
                    } else {
                        Variables.getGui().getAminoSequence().getComponent(i).setForeground(Variables.getColorOfMod());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfMod());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setEnabled(true);

                    }
                }
            } else {
                if (pref && i >= end) {
                    Variables.getGui().getAminoSequence().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                } else if (!pref && i < start) {
                    Variables.getGui().getAminoSequence().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                }
            }
        }
        if (!flah) {
            JPopupMenu pm = new JPopupMenu();
            pm.add(new JLabel("There is no neither modifications nor substitutions in this " + (pref ? "prefix" : "suffix")));
            pm.show(Variables.getGui().getScrollPane(), 0, 50);
        }

    }

    private void findModifications(AminoAcid a, int num) {
        ArrayList<Modification> al = new ArrayList<>();
        for (Modification mod : Variables.getModificationDataBase().get(a)) {
            double dm1 = mod.getMassDifference();
            double mistake = countTMPMistake(num);
            if (dm1 >= dm - mistake && dm1 <= dm + mistake) {
                al.add(mod);
            }
        }
        Variables.getTmpModifications().put(num, al);

    }

    private void findCandidatesForThisAmino(AminoAcid a, int num) {
        ArrayList<AminoAcid> al = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (isFit(a, AminoAcid.values()[i], num)) {
                al.add(AminoAcid.values()[i]);
            }
        }
        Variables.getCandidates().put(num, al);
    }

    private boolean isFit(AminoAcid a, AminoAcid a1, int num) {
        return isFitByMass(a, a1, num) && isSMP(a, a1) && !a.equals(a1);
    }

    private boolean isFitByMass(AminoAcid a, AminoAcid a1, int num) {
        double dm1 = a.getMass() - a1.getMass();
        double mistake = countTMPMistake(num);
        return (dm1 <= dm + mistake && dm1 >= dm - mistake);
    }

    private boolean isSMP(AminoAcid a1, AminoAcid a2) {
        for (int j = 0; j < a1.getCodons().length; j++) {
            for (int k = 0; k < a2.getCodons().length; k++) {
                if (isSMP(a1.getCodons()[j], a2.getCodons()[k])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSMP(String a, String a1) {
        return ((a.charAt(0) == a1.charAt(0) && (a.charAt(1) == a1.charAt(1)))
                || (a.charAt(0) == a1.charAt(0) && a.charAt(2) == a1.charAt(2))
                || (a.charAt(1) == a1.charAt(1) && (a.charAt(2) == a1.charAt(2))))
                && !(a.charAt(0) == a1.charAt(0) && a.charAt(1) == a1.charAt(1) && (a.charAt(2) == a1.charAt(2)));
    }
}

