package ActionListeners;

import GUI.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = GUI.tf.getText();
        if (input.compareTo("") == 0) {
            return;
        }
        String[] aminos = input.split("\\s+");
        JButton[] buttons = new JButton[aminos.length];
        for (int i = 0; i < aminos.length; i++) {
            buttons[i] = new JButton(aminos[i]);
            buttons[i].setSize(3, 1);
            buttons[i].setActionCommand(aminos[i]);
            buttons[i].addActionListener(new CreatingCodons());
            GUI.panelBottom.add(buttons[i]);
        }
        GUI.tf.setText("");
        new GUI("GUI");

    }
}
