package ActionListeners;

import Start.*;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Scrolling implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (Start.isSubmitted()) {
            int value = e.getValue();
            int start = value / 60;

            int end = (value >= start * 60 + 35 && value <= (start + 1) * 60) ? Start.countInScrollPanel + 1 : Start.countInScrollPanel;
            end += start;
            int i = start;
            while (i < end && i < Start.getGui().getAminoPanel().getComponentCount()) {
                Start.getGui().getPanelTop().getComponent(i).setForeground(Color.RED);
                i++;
            }
            if (start > 0) {
                Start.getGui().getPanelTop().getComponent(start - 1).setForeground(Color.WHITE);
            }
            if (end < Start.getGui().getAminoPanel().getComponentCount()) {
                Start.getGui().getPanelTop().getComponent(end).setForeground(Color.WHITE);
            }
        }

    }
}
