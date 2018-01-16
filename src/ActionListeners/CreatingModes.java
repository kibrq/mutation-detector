package ActionListeners;

import GUI.GUI;
import Start.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class CreatingModes implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        GUI gui = Start.getGui();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                Start.setIsShiftPressed(true);
                break;
            case KeyEvent.VK_F1:
                if (Start.isShiftPressed()) {
                    Start.setIsCompareMode(!Start.isCompareMode());
                    Start.setIsMassDiff(false);
                    if (Start.isCompareMode()) {
                        gui.setTitle("Main(compare mode)");
                    } else {
                        gui.setTitle("Main");
                    }
                    if (Start.panel1 != null) {
                        Start.panel1.setVisible(false);
                        Start.panel1 = null;
                    }
                    if (Start.panel2 != null) {
                        Start.panel2.setVisible(false);
                        Start.panel2 = null;
                    }
                    gui.getSecondAmino().removeAll();
                    gui.getSecondAmino().repaint();
                    gui.getPanelLines().removeAll();
                    gui.getPanelLines().revalidate();
                    gui.getPanelLines().repaint();
                    gui.getNavigationPanel().removeAll();
                    gui.getNavigationPanel().revalidate();
                    gui.getNavigationPanel().repaint();

                }
                break;
            case KeyEvent.VK_F2:
                if (Start.isShiftPressed()) {

                    Start.setIsMassDiff(!Start.isMassDiff());
                    Start.setIsCompareMode(false);
                    if (Start.isMassDiff()) {
                        gui.setTitle("Main(mass difference mode)");
                    } else {
                        gui.setTitle("Main");
                    }
                    if (Start.panel1 != null) {
                        Start.panel1.setVisible(false);
                        Start.panel1 = null;
                    }
                    if (Start.panel2 != null) {
                        Start.panel2.setVisible(false);
                        Start.panel2 = null;
                    }
                    gui.getPanelLines().removeAll();
                    gui.getPanelLines().revalidate();
                    gui.getPanelLines().repaint();
                }
                break;
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
