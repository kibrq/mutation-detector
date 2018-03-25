package Listeners.ActionListners.AminoButton;

import GUI.GUI;
import Global.Variables;

import java.awt.*;

public class Help {
    public static void repaintPanelTop()

    {
        GUI gui = Variables.getGui();
        int left = gui.getScrollPane().getHorizontalScrollBar().getValue();
        for (int i = 0; i < gui.getPanelTop().getComponentCount() - 2; i++) {
            if (gui.getPanelTop().getComponent(i).getForeground() == Variables.colorOfChoosen) {
                gui.getAminoPanel().getComponent(i).setForeground(Color.BLACK);
                if (i * (Variables.buttonWidth + 10) >= left && i * (Variables.buttonWidth + 10) <= left + (Variables.countInScrollPanel * (Variables.buttonWidth + 10))) {
                    gui.getPanelTop().getComponent(i).setForeground(Color.RED);
                } else
                    gui.getPanelTop().getComponent(i).setForeground(Color.WHITE);
            }
        }
        gui.getPanelTop().repaint();
        gui.getPanelTop().revalidate();
    }

    public static void removeAllFromUnderBottomPanel() {
        GUI gui = Variables.getGui();
        gui.getFirstAmino().removeAll();
        gui.getSecondAmino().removeAll();
        gui.getPanelLines().removeAll();
        gui.getFirstAmino().repaint();
        gui.getSecondAmino().repaint();
        gui.getPanelLines().repaint();
        gui.getFirstAmino().revalidate();
        gui.getSecondAmino().revalidate();
        gui.getPanelLines().revalidate();
        gui.getNavigationPanel().removeAll();
        gui.getNavigationPanel().repaint();
    }
}
