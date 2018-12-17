package AdditionalClasses;

import Global.Variables;
import Model.AminoAcid;
import Model.Modification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsefullFunctions {
    public static void parseModification() throws IOException {
        Variables.setBeginToModifications();
        Scanner sc = new Scanner(new File("modifications.in"));
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split("\\s+");
            String name = arr[0].substring(0, arr[0].length() - 1);
            String[] aminos = arr[1].split(",");
            AminoAcid[] aminos1 = new AminoAcid[aminos.length];
            double massDifference = round(Double.parseDouble(arr[2]));
            for (int i = 0; i < aminos.length; i++) {
                aminos1[i] = AminoAcid.valueOf(aminos[i]);
                Variables.getModificationDataBase().get(aminos1[i]).add(new Modification(name, massDifference));
                System.out.println(Variables.getModificationDataBase().get(aminos1[1]));
            }


        }

    }

    public static double round(double a) {
        a *= Math.pow(10, 6);
        a = Math.round(a);
        a /= Math.pow(10, 6);
        return a;
    }

    public static void revalidateRepaint(JComponent component) {
        component.repaint();
        component.revalidate();
    }

    public static void clearBackgrounds() {
        for (Component component : Variables.getGui().getAminoSequence().getComponents()) {
            component.setBackground(Variables.getColorOfPanelWithSequnece());
        }
        resetCur();
    }

    public static void clearAminoSequence() {
        clearBackgrounds();
        for (Component component1 : Variables.getGui().getAminoSequence().getComponents()) {
            Component component = ((JPanel) component1).getComponent(0);
            if (component.getForeground() != Variables.getColorInViewOfScroll() || component.getForeground() != Variables.getNormalColor()) {
                component.setForeground(Variables.getNormalColor());
            }
        }
        Variables.getGui().getScrollPane().getHorizontalScrollBar().getAdjustmentListeners()[0].adjustmentValueChanged(new AdjustmentEvent(new MyAdjustable(), 0, 0, Variables.getGui().getScrollPane().getHorizontalScrollBar().getValue()));
        for (Component component : Variables.getGui().getPanelWithAminoButtons().getComponents()) {

            if (component.getForeground() != Variables.getNormalColor()) {
                component.setForeground(Variables.getNormalColor());
            }
        }
        Variables.clearPanelsWithModCod();
    }

    public static void prefixAction() {
        Variables.setPrefixSelecting(!Variables.isPrefixSelecting());
        Variables.getGui().getScrollPane().getHorizontalScrollBar().setValue(Variables.getGui().getScrollPane().getHorizontalScrollBar().getMaximum());
        enableUnenableSuffixPrefix();
        UsefullFunctions.clearAminoSequence();
    }

    public static void suffixAction() {
        Variables.setSuffixSelecting(!Variables.isSuffixSelecting());
        Variables.getGui().getScrollPane().getHorizontalScrollBar().setValue(0);
        enableUnenableSuffixPrefix();
        UsefullFunctions.clearAminoSequence();
    }

    public static void enableUnenableSuffixPrefix() {
        Variables.getGui().getPrefix().setEnabled(!(Variables.isPrefixSelecting() || Variables.isSuffixSelecting()));
        Variables.getGui().getSuffix().setEnabled(!(Variables.isPrefixSelecting() || Variables.isSuffixSelecting()));
    }


    public static class MyAdjustable implements Adjustable {

        @Override
        public int getOrientation() {
            return 0;
        }

        @Override
        public void setMinimum(int min) {

        }

        @Override
        public int getMinimum() {
            return 0;
        }

        @Override
        public void setMaximum(int max) {

        }

        @Override
        public int getMaximum() {
            return 0;
        }

        @Override
        public void setUnitIncrement(int u) {

        }

        @Override
        public int getUnitIncrement() {
            return 0;
        }

        @Override
        public void setBlockIncrement(int b) {

        }

        @Override
        public int getBlockIncrement() {
            return 0;
        }

        @Override
        public void setVisibleAmount(int v) {

        }

        @Override
        public int getVisibleAmount() {
            return 0;
        }

        @Override
        public void setValue(int v) {

        }

        @Override
        public int getValue() {
            return 0;
        }

        @Override
        public void addAdjustmentListener(AdjustmentListener l) {

        }

        @Override
        public void removeAdjustmentListener(AdjustmentListener l) {

        }
    }

    public static void drawInCenterOfRect(Graphics2D g2d, String s, int y, int startX, int endX) {
        g2d.drawString(s, XCenter(s, g2d.getFont(), startX, endX), y);
    }

    public static void drawInCenter(JComponent comp, Graphics2D g2d, String s, int y) {
        g2d.drawString(s, XCenter(0, comp.getWidth(), s, g2d.getFont()), y);
    }

    public static int XCenter(String s, Font font, int startX, int endX) {
        return ((endX - startX - s.length() * font.getSize() / 2) / 2);
    }

    public static int XCenter(int startX, int endX, String s, Font font) {
        return XCenter(startX, endX, s.length() * font.getSize() / 2);
    }

    private static int XCenter(int startX, int endX, int len) {
        return (endX - startX - len) / 2;
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

    private static Color colors[] = {new Color(180, 40, 240), new Color(130, 170, 80), new Color(50, 150, 200)};
    private static int cur = 0;

    public static Color spotHiglightedAminoAcids(ArrayList<Integer> numbers) {
        int i = 0;
        int k = 0;
        for (Component comp : Variables.getGui().getAminoSequence().getComponents()) {
            if (k == numbers.size()) {
                break;
            }
            if (i == numbers.get(k)) {
                comp.setBackground(colors[cur]);
                k++;
            }
            i++;
        }
        Color col = colors[cur];
        cur++;
        cur %= colors.length;
        return col;
    }

    private static void resetCur() {
        cur = 0;
    }

}
