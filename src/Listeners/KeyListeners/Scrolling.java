package Listeners.KeyListeners;

import Global.*;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import GUI.*;

public class Scrolling implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (Variables.isSubmitted()) {
            GUI gui = Variables.getGui();
            int wid = Variables.buttonWidth;
            int value = e.getValue();
            int start = value / wid;//first number of Amino which would be red

            int end = (value >= start * wid + (wid / 2) + 5 && value <= (start + 1) * wid) ? Variables.countInScrollPanel + 1 : Variables.countInScrollPanel;//it is checking for transition location of ScrollBar
            end += start;
            int i = start;
            for (int j = 0; j < gui.getAminoPanel().getComponentCount(); j++) {
                if (gui.getPanelTop().getComponent(j).getForeground() == Color.RED || gui.getPanelTop().getComponent(j).getForeground() == Color.WHITE) {
                    if (j >= i && j < end) {
                        gui.getPanelTop().getComponent(j).setForeground(Color.RED);
                    } else {
                        gui.getPanelTop().getComponent(j).setForeground(Color.WHITE);
                    }
                }
            }

        }
    }
}
