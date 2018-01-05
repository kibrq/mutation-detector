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
        DataBase db = Start.getDb();

        int x = 900;
        int y = -30;
        int len = aminos.length;
        for (int i = 0; i < aminos.length; i++) {
            if (x >= 900 - 50) {
                y += 30;
                if (i != 0) {
                    len = len - 900 / 60;
                }
                int count = (len) <= 900 / 60 ? len : 900 / 60;
                int left = 900 - (count * 50 + 10 * (count - 1));
                x = left / 2;
            } else {
                x = x + 60;
            }

            JButton button = new JButton(Character.toString(aminos[i]));
            int k = -1;
            for (int l = 0; l < db.getDatabase().size(); l++) {
                if (Character.toString(aminos[i]).compareTo(db.getDatabase().get(l).getTitle()) == 0) {
                    k = l;
                }
            }
            button.setName(Character.toString(aminos[i]));
            button.addActionListener(new CreatingCodons());
            button.setBounds(x, y, 50, 20);
            button.addKeyListener(new CreatingCompareMode());

            Start.getGui().getPanelBottom().add(button);
        }
        JButton but = (JButton)e.getSource();
        but.setFocusable(false);
        Start.getGui().showGui();
        Start.getGui().getContentPane().setFocusable(true);

    }
}
