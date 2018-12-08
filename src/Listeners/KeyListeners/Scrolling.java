package Listeners.KeyListeners;

import Global.*;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import GUI.*;

import javax.swing.*;

public class Scrolling implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        GUI gui = Variables.getGui();
        int width = Variables.getButtonWidth();
        int count = gui.getScrollPane().getWidth() / (Variables.getButtonWidth() + 1);
        int x = e.getValue() - 5;
        int start = (x) / (width + 5);
        if (x > (width + 5) * start + width / 2) {
            start++;
            count--;
            if (x > (width + 5) * (start - 1) + width - 5) {
                count++;
            }
        }
        int i = 0;
        for (Component component1 : gui.getAminoSequence().getComponents()) {
            Component component = ((JPanel) component1).getComponent(0);
            if (component.getForeground() == Variables.getNormalColor() || component.getForeground() == Variables.getColorInViewOfScroll()) {
                if (i >= start && i < start + count) {
                    component.setForeground(Variables.getColorInViewOfScroll());
                } else {
                    component.setForeground(Variables.getNormalColor());
                }
            }
            i++;

        }


    }

}

