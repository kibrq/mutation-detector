package Listeners.ActionListners.SubmitButton;

import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Global.Variables;
import Listeners.ActionListners.AminoButton.CreatingCodons;
import Listeners.KeyListeners.CreatingModes;
import Listeners.KeyListeners.PrefixSuffixSelect;
import Model.Amino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GenerateAminos {
    public static void generate() {
        UsefullFunctions.repaintPanelTop();
        double waterMass = 18.01057;
        GUI gui = Variables.getGui();
        String input = gui.getTf().getText().toUpperCase();
        if (input.compareTo("") == 0) {
            gui.getAminoPanel().repaint();
            gui.getScrollPane().revalidate();
            return;
        }
        gui.getAminoPanel().removeAll();
        char[] aminos = input.toCharArray();
        Variables.massesPrefix = new double[aminos.length];
        Variables.massesSuffix = new double[aminos.length];
        Variables.seq = new Amino[aminos.length];
        ArrayList<Amino> al = Variables.getDb().getDatabase();
        for (int i = 0; i < aminos.length; i++) {
            boolean flag = false;
            for (int j = 0; j < al.size(); j++) {
                if (aminos[i] == al.get(j).getTitle().charAt(0)) {
                    flag = true;
                    Variables.seq[i] = al.get(j);
                    Variables.massesPrefix[i] = i == 0 ? al.get(j).getMass()+waterMass : Variables.massesPrefix[i - 1] + al.get(j).getMass()+waterMass;
                    Variables.massesSuffix[i] = al.get(j).getMass() + waterMass;
                }
            }
            if (!flag) {
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(new JLabel("The input is incorrect"));
                popupMenu.show(gui.getBut(), 0, 0);
                gui.getAminoPanel().removeAll();
                return;
            }

            JButton button = new JButton(Character.toString(aminos[i]));
            button.setName(Integer.toString(i));
            button.addActionListener(new CreatingCodons());
            button.addKeyListener(new CreatingModes());
            button.addKeyListener(new PrefixSuffixSelect());
            button.setPreferredSize(new Dimension(50, 20));
            gui.getAminoPanel().add(button);
        }
        for (int i = aminos.length - 2; i >= 0; i--) {
            Variables.massesSuffix[i] += Variables.massesSuffix[i + 1];
        }
        System.out.println(Variables.massesSuffix[0]+" "+Variables.massesPrefix[Variables.massesPrefix.length-1]);
        gui.getAminoPanel().repaint();
        gui.getScrollPane().revalidate();
        Variables.panel1 = null;
        Variables.panel2 = null;

        Variables.setSubmitted(true);
        gui.getBut().setText("Change");
        UsefullFunctions.refreshPanelTop();


    }
}
