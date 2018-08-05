package Listeners.KeyListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Global.*;



public class PrefixSuffixSelect implements KeyListener {
    private boolean check() {
        if (Variables.isMassDiff()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!check()) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                Variables.isPrefixSelect = true;
                break;
            case KeyEvent.VK_S:
                Variables.isSuffixSelect = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_S:
                Variables.isSuffixSelect = false;
                break;
            case KeyEvent.VK_P:
                Variables.isPrefixSelect = false;
                break;
        }
    }
}