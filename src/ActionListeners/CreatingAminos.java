package ActionListeners;

import GUI.GUI;
import Start.Start;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


            for (int i = 0; i < aminos.length; i++) {
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
                label.setText(Character.toString(input.charAt(i)));
                gui.getPanelTop().add(label);
            }
            double a = gui.getInputDM().getText().compareTo("")==0?0:Double.parseDouble(Start.getGui().getInputDM().getText());
            JLabel label = new JLabel("    " +a+"g");
            label.setForeground(Color.WHITE);
            gui.getPanelTop().add(label);

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
