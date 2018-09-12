package AdditionalClasses;

import GUI.GUI;
import Global.Variables;
import Listeners.ActionListners.AminoButton.CreatingCodons;
import Model.Amino;
import Model.Modification;

import javax.management.MalformedObjectNameException;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UsefullFunctions {
    public static void parseModification() throws IOException {
        Scanner sc = new Scanner(new File("modifications.in"));
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] arr = s.split("\\s+");
            System.out.println(arr[1]);
            String name = arr[0].substring(0, arr[0].length() - 1);
            String[] aminos = arr[1].split(",");
            Amino[] aminos1 = new Amino[aminos.length];
            for (int i = 0; i < aminos.length; i++) {
                aminos1[i] = new Amino(aminos[i]);
                System.out.println(aminos1[i]);
            }

            double massDifference = Double.parseDouble(arr[2]);
            Variables.getModificationDataBase().add(new Modification(name, aminos1, massDifference));
        }
    }

    public static HashMap<Integer, ArrayList<Modification>> findPossibleModification(boolean isPrefix) {
        double fpOfMistake = (Double.parseDouble(Variables.getGui().getInputPPM().getText())) / Math.pow(10, 6);
        double md = Double.parseDouble(Variables.getGui().getInputDM().getText());
        double mistake;
        if (isPrefix) {
            mistake = Variables.massesPrefix[Variables.numPrefix] * fpOfMistake;
        } else {
            mistake = Variables.massesSuffix[Variables.numSuffix] * fpOfMistake;
        }
        System.out.println(mistake);

        ArrayList<Modification> possibleMods = new ArrayList<>();

        for (Modification mod : Variables.getModificationDataBase()) {
            if (mod.getMassDifference() < md + mistake && mod.getMassDifference() > md - mistake) {
                possibleMods.add(mod);
            }
        }
        System.out.println(possibleMods.toString());
        int startIndex = isPrefix ? 0 : Variables.numSuffix;
        int endIndex = isPrefix ? Variables.numPrefix : Variables.seq.length - 1;

        HashMap<Integer, ArrayList<Modification>> res = new HashMap<>();

        for (int i = startIndex; i <= endIndex; i++) {
            ArrayList<Modification> possibleForThisAmino = new ArrayList<>();
            for (Modification mod : possibleMods) {
                if (mod.contains(Variables.seq[i])) {
                    possibleForThisAmino.add(mod);
                }
            }
            res.put(i, possibleForThisAmino);
        }
        System.out.println(res);
        return res;
    }
    public static void repaintPanelTop() {
        GUI gui = Variables.getGui();
        int left = gui.getScrollPane().getHorizontalScrollBar().getValue();
        for (int i = 0; i < gui.getPanelTop().getComponentCount() - 2; i++) {
            Color col = gui.getPanelTop().getComponent(i).getForeground();
            if (col == Variables.colorOfChoosen||col == Variables.colorOfChoosenByModification||col==Variables.colorOfChoosenByChange) {
                gui.getAminoPanel().getComponent(i).setForeground(Color.BLACK);
                if (i * (Variables.buttonWidth + 10) >= left && i * (Variables.buttonWidth + 10) <= left + (Variables.countInScrollPanel * (Variables.buttonWidth + 10))) {
                    gui.getPanelTop().getComponent(i).setForeground(Color.RED);
                } else
                    gui.getPanelTop().getComponent(i).setForeground(Color.WHITE);
            }
        }
        gui.getPanelTop().repaint();
        gui.getPanelTop().revalidate();
    }

    public static void removeAllFromUnderBottomPanel() {
        GUI gui = Variables.getGui();
        gui.getFirstAmino().removeAll();
        gui.getSecondAmino().removeAll();
        gui.getPanelLines().removeAll();
        gui.getFirstAmino().repaint();
        gui.getSecondAmino().repaint();
        gui.getPanelLines().repaint();
        gui.getFirstAmino().revalidate();
        gui.getSecondAmino().revalidate();
        gui.getPanelLines().revalidate();
        gui.getNavigationPanel().removeAll();
        gui.getNavigationPanel().repaint();
        gui.getPanelUnderBottomRight().removeAll();
        gui.getPanelUnderBottomRight().repaint();
        gui.getPanelUnderBottomRight().revalidate();
    }
    public static void underlineAminos(int beginIndex, int endIndex){
        JPanel panel = Variables.getGui().getPanelTop();
        for(int i = beginIndex; i<endIndex;i++){
            JLabel label = (JLabel) panel.getComponent(i);
            label.setText("<HTML><U>"+label.getText()+"</U></HTML>");

        }
        panel.repaint();

    }
    public static void refreshPanelTop(){
        GUI gui = Variables.getGui();
        String input = gui.getTf().getText().toUpperCase();
        gui.getPanelTop().removeAll();
        char[] aminos = input.toCharArray();
        Color col = aminos.length < 10 ? Color.RED : Color.WHITE;
        for (int i = 0; i < input.length(); i++) {
            JLabel label = new JLabel();
            label.setForeground(col);
            label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            label.setText(Character.toString(input.charAt(i)));
            gui.getPanelTop().add(label);
        }
        double a = gui.getInputDM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Variables.getGui().getInputDM().getText());
        JLabel label = new JLabel("    " + a + "Da");
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        gui.getPanelTop().add(label);
        a = gui.getInputPPM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Variables.getGui().getInputPPM().getText());
        JLabel label1 = new JLabel("    " + a + "ppm");
        label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        label1.setForeground(Color.WHITE);
        JLabel label2 = new JLabel("    Current mistake:  " + Variables.getCurrentMistake() +"Da");
        label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        gui.setCurrentMistake(label2);
        label2.setForeground(Color.WHITE);
        gui.getPanelTop().add(label1);
        gui.getPanelTop().add(label2);
        gui.getPanelTop().repaint();
    }
}
