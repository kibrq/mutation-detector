package ActionListeners;

import Start.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FillingForm implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            JTextField tf = (JTextField) e.getSource();
            switch (tf.getName()) {
                case "1":
                    Start.getGui().getTf().setFocusable(false);
                    Start.getGui().getInputDM().setFocusable(true);
                    break;
                case "2":
                    Start.getGui().getInputDM().setFocusable(false);
                    Start.getGui().getInputPPM().setFocusable(true);
                    break;
                case "3":
                    Start.getGui().getInputPPM().setFocusable(false);
                    Start.getGui().getBut().doClick();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
