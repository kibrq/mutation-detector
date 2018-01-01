package ActionListeners;

import Start.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CreatingCompareMode implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Start.isCompareMode = !Start.isCompareMode;
                System.out.println(Start.isCompareMode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override

    public void keyTyped(KeyEvent e) {

    }
}
