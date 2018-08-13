package Listeners.KeyListeners;

import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Global.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static AdditionalClasses.UsefullFunctions.removeAllFromUnderBottomPanel;


public class CreatingModes implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        GUI gui = Variables.getGui();

        switch (e.getKeyCode()) {

            case KeyEvent.VK_SHIFT:
                Variables.setIsShiftPressed(true);
                Variables.isPrefixSelect = false;
                Variables.isSuffixSelect = false;
                break;
            case KeyEvent.VK_F1:
                if (Variables.isShiftPressed()) {
                    Variables.setIsCompareMode(!Variables.isCompareMode());
                    Variables.setIsMassDiff(false);
                    if (Variables.isCompareMode()) {
                        Start.frame.setTitle("Main(compare mode)");
                        Variables.getGui().getPanelUnderBottom().removeAll();
                        Variables.getGui().fillingUnderBottomPanel(false);
                        Variables.getGui().getPanelUnderBottom().revalidate();
                        Variables.getGui().getPanelUnderBottom().repaint();

                    } else {
                        Start.frame.setTitle("Main");
                    }
                    if (Variables.panel1 != null) {
                        Variables.panel1.setVisible(false);
                        Variables.panel1 = null;
                    }
                    if (Variables.panel2 != null) {
                        Variables.panel2.setVisible(false);
                        Variables.panel2 = null;
                    }
                    UsefullFunctions.removeAllFromUnderBottomPanel();
                    UsefullFunctions.repaintPanelTop();

                }
                break;
            case KeyEvent.VK_F2:
                if (Variables.isShiftPressed()) {

                    Variables.setIsMassDiff(!Variables.isMassDiff());
                    Variables.setIsCompareMode(false);
                    if (Variables.isMassDiff()) {
                        Start.frame.setTitle("Main(mass difference mode)");
                        Variables.getGui().getPanelUnderBottom().removeAll();
                        Variables.getGui().fillingUnderBottomPanel(true);
                        Variables.getGui().getPanelUnderBottom().revalidate();
                        Variables.getGui().getPanelUnderBottom().repaint();
                    } else {
                        Start.frame.setTitle("Main");
                        Variables.getGui().getPanelUnderBottom().removeAll();
                        Variables.getGui().fillingUnderBottomPanel(false);
                        Variables.getGui().getPanelUnderBottom().revalidate();
                        Variables.getGui().getPanelUnderBottom().repaint();
                    }
                    if (Variables.panel1 != null) {
                        Variables.panel1.setVisible(false);
                        Variables.panel1 = null;
                    }
                    if (Variables.panel2 != null) {
                        Variables.panel2.setVisible(false);
                        Variables.panel2 = null;
                    }
                    UsefullFunctions.removeAllFromUnderBottomPanel();
                    UsefullFunctions.repaintPanelTop();
                }
                break;
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:
                Variables.setIsShiftPressed(false);
                break;
        }
    }

    @Override

    public void keyTyped(KeyEvent e) {

    }
}
