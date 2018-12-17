package Listeners.ActionListners.AminoButton;

import AdditionalClasses.UsefullFunctions;

import Global.Variables;
import Model.AminoAcid;
import Model.Mode;
import Model.Modification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class AminoAcidClicked implements ActionListener {
    int i = 0;
    double ppm = 0;
    double dm = 0;


    @Override
    public void actionPerformed(ActionEvent e) {
        dm = Variables.getDm();
        ppm = Variables.getPpm();
        Variables.getGui().removeAllAminoAcids();
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
                    if (Variables.getCandidates() != null && !Variables.getCandidates().get(i).isEmpty()) {
                        Variables.setA2(Variables.getCandidates().get(i).get(Variables.getCurrentCandidate()));
                    } else {
                        Variables.setA2(null);
                    }
                    break;

            }
            ArrayList<ArrayList<Integer>> sb = new ArrayList<>();
            int cur = 0;
            if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
                int previous = 0;
                while (!Variables.getSeq()[previous].getTitle().equals(Variables.getSeq()[i].getTitle())) {
                    previous++;
                }
                System.out.println(previous);
                sb.add(new ArrayList<>());
                sb.get(cur).add(previous);
                for (int j = previous + 1; j < Variables.getSeq().length; j++) {
                    if (Variables.getCandidates().get(j) != null && Variables.getSeq()[j].getTitle().equals(Variables.getSeq()[previous].getTitle())) {
                        if (Variables.aminoAcidsEquals(previous, j)) {
                            sb.get(cur).add(j);
                            System.out.println(sb);
                        } else {
                            sb.add(new ArrayList<>());
                            cur++;
                            sb.get(cur).add(j);
                            previous = j;
                        }
                    }
                }
            } else {
                sb.add(new ArrayList<>());
                sb.get(0).add(i);
            }
            Variables.getGui().addAminoAcid(sb);

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
                    ((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).setForeground(Variables.getColorOfMut());
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
                    if (((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).getForeground() == Variables.getColorOfMut()) {
                        ((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).setForeground(Variables.getColorOfBoth());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfBoth());
                    } else {
                        ((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).setForeground(Variables.getColorOfMod());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfMod());
                        Variables.getGui().getPanelWithAminoButtons().getComponent(i).setEnabled(true);

                    }
                }
            } else {
                if (pref && i >= end) {
                    ((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                } else if (!pref && i < start) {
                    ((JPanel) Variables.getGui().getAminoSequence().getComponent(i)).getComponent(0).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                    Variables.getGui().getPanelWithAminoButtons().getComponent(i).setForeground(Variables.getColorOfCheckedPrefix_Suffix());
                }
            }
        }
        if (!flah) {
            JPopupMenu pm = new JPopupMenu();
            pm.add(new JLabel("There is no neither modifications nor substitutions in this " + (pref ? "prefix" : "suffix")));
            pm.show(Variables.getGui().getScrollPane(), 0, 50);
        }
        UsefullFunctions.enableUnenableSuffixPrefix();

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
        double dm1 = a.getDifferenceMass(a1);
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

