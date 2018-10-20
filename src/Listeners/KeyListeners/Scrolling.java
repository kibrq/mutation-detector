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
        int wid = Variables.getButtonWidth();
        int value = e.getValue();
        int start = value / wid;//first number of Amino which would be red
        int end = (value >= start * wid + (wid / 2) + 5 && value <= (start + 1) * wid) ? Variables.getCountInScrollPanel() + 1 : Variables.getCountInScrollPanel();//it is checking for transition location of ScrollBar
        end += start;
        int i = start;
        JPanel panel = gui.getAminoSequence();
        for (int j = 0; j < panel.getName().length(); j++) {
            if (panel.getComponent(j).getForeground() == Variables.getNormalColor() || panel.getComponent(j).getForeground() == Variables.getColorInViewOfScroll()) {
                if (j >= i && j < end) {

                    panel.getComponent(j).setForeground(Variables.getColorInViewOfScroll());
                } else {
                    panel.getComponent(j).setForeground(Variables.getNormalColor());
                }
            }
        }

    }
}

