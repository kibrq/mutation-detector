package Listeners.KeyListeners;

import Global.Variables;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChangePPM implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Variables.setPpm(Double.parseDouble(Variables.getGui().getMistakePPM().getText()));
            JPopupMenu pm = new JPopupMenu();
            pm.add(new JLabel("ppm has been changed"));
            pm.show(Variables.getGui().getMistakePPM(), -100, 50);
            Variables.getGui().getMistakePPM().setFocusable(false);
            Variables.getGui().getChangePPM().setText("Change");
            Variables.getGui().getChangePPM().setName("Change");

        }
    }
}
