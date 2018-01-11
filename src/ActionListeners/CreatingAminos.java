package ActionListeners;

import Database.DataBase;
import Start.Start;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton but =(JButton)e.getSource();
        if(!Start.isSubmitted()) {
            String input = Start.getGui().getTf().getText().toUpperCase();
            if (input.compareTo("") == 0) {
                Start.getGui().getAminoPanel().repaint();
                Start.getGui().getScrollPane().revalidate();
                return;
            }

            Start.getGui().getAminoPanel().removeAll();
            char[] aminos = input.toCharArray();


            for (int i = 0; i < aminos.length; i++) {
                JButton button = new JButton(Character.toString(aminos[i]));
                button.setName(Character.toString(aminos[i]));
                button.addActionListener(new CreatingCodons());
                button.addKeyListener(new CreatingModes());
                button.setPreferredSize(new Dimension(50, 20));
                Start.getGui().getAminoPanel().add(button);
            }
            Start.getGui().getAminoPanel().repaint();
            Start.getGui().getScrollPane().revalidate();
            Start.panel1 = null;
            Start.panel2 = null;

            Start.setSubmitted(true);
            but.setText("Change");
            Start.getGui().getPanelTop().removeAll();
            for (int i = 0; i < input.length(); i++) {
                JLabel label = new JLabel();
                label.setForeground(Color.WHITE);
                label.setText(Character.toString(input.charAt(i)));
                Start.getGui().getPanelTop().add(label);
            }
            JLabel label = new JLabel("    "+Start.getGui().getInputDM().getText());
            label.setForeground(Color.WHITE);
            Start.getGui().getPanelTop().add(label);

            Start.getGui().getPanelTop().repaint();
        }else{

            Start.getGui().settingPanelTop();
            Start.setSubmitted(false);
            but.setText("Submit");
            Start.getGui().getPanelTop().repaint();
            if (Start.panel1 != null) {
                Start.panel1.setVisible(false);
                Start.panel1 = null;
            }
            if (Start.panel2 != null) {
                Start.panel2.setVisible(false);
                Start.panel2 = null;
            }
            Start.getGui().getPanelLines().removeAll();
            Start.getGui().getPanelUnderBottom().repaint();

        }

    }
}
