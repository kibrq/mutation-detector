package ActionListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Start.*;



public class PrefixSuffixSelect implements KeyListener {
    private boolean check() {
        if (Start.isMassDiff()) {
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
                Start.isPrefixSelect = true;
                break;
            case KeyEvent.VK_S:
                Start.isSuffixSelect = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_S:
                Start.isSuffixSelect = false;
                break;
            case KeyEvent.VK_P:
                Start.isPrefixSelect = false;
                break;
        }
    }
}
