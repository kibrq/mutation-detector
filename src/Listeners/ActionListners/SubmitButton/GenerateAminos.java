package Listeners.ActionListners.SubmitButton;

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
                    Variables.massesPrefix[i] = i == 0 ? al.get(j).getMass() : Variables.massesPrefix[i - 1] + al.get(j).getMass();
                    Variables.massesSuffix[i] = al.get(j).getMass();
                }
            }
            if(!flag){
                JPopupMenu popupMenu = new JPopupMenu();
                popupMenu.add(new JLabel("The input is incorrect" ));
                popupMenu.show(gui.getBut(), 0,0);
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
        gui.getAminoPanel().repaint();
        gui.getScrollPane().revalidate();
        Variables.panel1 = null;
        Variables.panel2 = null;

        Variables.setSubmitted(true);
        gui.getBut().setText("Change");
        gui.getPanelTop().removeAll();
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
        gui.getPanelTop().add(label1);
        gui.getPanelTop().repaint();

    }
}
