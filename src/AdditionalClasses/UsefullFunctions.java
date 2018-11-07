package AdditionalClasses;

import Global.Variables;
import Model.AminoAcid;
import Model.Modification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
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

    public static void clearAminoSequence() {
        for (Component component : Variables.getGui().getAminoSequence().getComponents()) {
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
        UsefullFunctions.clearAminoSequence();
    }

    public static void suffixAction() {
        Variables.setSuffixSelecting(!Variables.isSuffixSelecting());
        Variables.getGui().getScrollPane().getHorizontalScrollBar().setValue(0);
        UsefullFunctions.clearAminoSequence();
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
}
