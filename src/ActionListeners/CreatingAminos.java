package ActionListeners;

import Database.DataBase;
import Start.Start;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        String input = Start.getGui().getTf().getText().toUpperCase();
        if (input.compareTo("") == 0) {
            return;
        }
        Start.getGui().getPanelBottom().removeAll();
        char[] aminos = input.toCharArray();

        int x = Integer.MAX_VALUE;
        int y = -30;
        int len = aminos.length;
        int wid = Start.getGui().getWidth();
        int inOneString = wid / 60;
        for (int i = 0; i < aminos.length; i++) {
            if (i == 0 || i % inOneString == 0) {
                y += 30;
                if (i != 0) {
                    len -= inOneString;

                }
                int count = (len) <= inOneString ? len : inOneString;
                int left = wid - (count * 50 + 10 * (count - 1));
                x = left / 2;
            } else {
                x = x + 60;
            }

            JButton button = new JButton(Character.toString(aminos[i]));
            button.setName(Character.toString(aminos[i]));
            button.addActionListener(new CreatingCodons());
            button.setBounds(x, y, 50, 20);
            button.addKeyListener(new CreatingCompareMode());

            Start.getGui().getPanelBottom().add(button);
        }
        Start.getGui().getPanelBottom().repaint();
        Start.panel1 = null;
        Start.panel2 = null;

    }
}
