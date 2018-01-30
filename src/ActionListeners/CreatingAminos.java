package ActionListeners;

import Database.DataBase;
import GUI.GUI;
import Model.Amino;
import Start.Start;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but = (JButton) e.getSource();
        GUI gui = Start.getGui();
        if (!Start.isSubmitted()) {
            String input = gui.getTf().getText().toUpperCase();
            if (input.compareTo("") == 0) {
                gui.getAminoPanel().repaint();
                gui.getScrollPane().revalidate();
                return;
            }
            gui.getAminoPanel().removeAll();
            char[] aminos = input.toCharArray();
            Start.massesPrefix = new double[aminos.length];
            ArrayList<Amino> al = Start.getDb().getDatabase();
            for (int i = 0; i < aminos.length; i++) {
                for (int j = 0; j < al.size(); j++) {
                    if (aminos[i] == al.get(j).getTitle().charAt(0)) {
                        Start.massesPrefix[i] = i == 0 ? al.get(j).getMass() : Start.massesPrefix[i - 1] + al.get(j).getMass();
                    }
                }

                JButton button = new JButton(Character.toString(aminos[i]));
                button.setName(Character.toString(aminos[i]));
                button.addActionListener(new CreatingCodons());
                button.addKeyListener(new CreatingModes());
                button.setPreferredSize(new Dimension(50, 20));
                gui.getAminoPanel().add(button);
            }
            gui.getAminoPanel().repaint();
            gui.getScrollPane().revalidate();
            Start.panel1 = null;
            Start.panel2 = null;

            Start.setSubmitted(true);
            but.setText("Change");
            gui.getPanelTop().removeAll();
            for (int i = 0; i < input.length(); i++) {
                JLabel label = new JLabel();
                label.setForeground(Color.WHITE);
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
                label.setText(Character.toString(input.charAt(i)));
                gui.getPanelTop().add(label);
            }
            double a = gui.getInputDM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Start.getGui().getInputDM().getText());
            JLabel label = new JLabel("    " + a + "Da");
            label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            label.setForeground(Color.WHITE);
            gui.getPanelTop().add(label);
            a = gui.getInputPPM().getText().compareTo("") == 0 ? 0 : Double.parseDouble(Start.getGui().getInputPPM().getText());
            JLabel label1 = new JLabel("    " + a + "ppm");
            label1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            label1.setForeground(Color.WHITE);
            gui.getPanelTop().add(label1);
            short flag = (short) (Start.switchToRed ? 1 : 0);
            gui.getScrollPane().getHorizontalScrollBar().setValue(flag);
            Start.switchToRed = !Start.switchToRed;
            gui.getPanelTop().repaint();

        } else {

            gui.settingPanelTop();
            Start.setSubmitted(false);
            but.setText("Submit");
            gui.getPanelTop().repaint();
            if (Start.panel1 != null) {
                Start.panel1.setVisible(false);
                Start.panel1 = null;
            }
            if (Start.panel2 != null) {
                Start.panel2.setVisible(false);
                Start.panel2 = null;
            }
            gui.getFirstAmino().removeAll();
            gui.getSecondAmino().removeAll();
            gui.getPanelLines().removeAll();
            gui.getPanelUnderBottom().repaint();
            gui.getNavigationPanel().removeAll();
            gui.getNavigationPanel().repaint();
            gui.getTf().setFocusable(true);


        }

    }
}
