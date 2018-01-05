package ActionListeners;

import Start.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class CreatingCompareMode implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                Start.setIsShiftPressed(true);
                break;
            case KeyEvent.VK_F1:
                if (Start.isShiftPressed()) {
                    Start.setIsCompareMode(!Start.isCompareMode());
                    if (Start.isCompareMode()) {
                        Start.getGui().setTitle("Main(compare mode)");
                    } else {
                        Start.getGui().setTitle("Main");
                    }

                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                Start.setIsShiftPressed(false);
                break;
        }
    }

    @Override

    public void keyTyped(KeyEvent e) {

    }
}
