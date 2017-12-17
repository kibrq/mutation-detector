package ActionListeners;

import GUI.GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.panelBottom.removeAll();
        String input = GUI.tf.getText();
        if (input.compareTo("") == 0) {
            return;
        }
        String[] aminos = input.split("\\s+");

        for (int i = 0; i < aminos.length; i++) {
            JButton button = new JButton(aminos[i]);
            button.setName(aminos[i]);
            button.addActionListener(new CreatingCodons());
            GUI.panelBottom.add(button);
        }
        GUI.tf.setText("");
        new GUI("main");


    }
}
