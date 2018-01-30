package ActionListeners;

import Start.*;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Scrolling implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        if (Start.isSubmitted()) {
            int wid = Start.buttonWidth;
            int value = e.getValue();
            int start = value / wid;

            int end = (value >= start * wid + (wid / 2) + 5 && value <= (start + 1) * wid) ? Start.countInScrollPanel + 1 : Start.countInScrollPanel;
            end += start;
            int i = start;
            for (int j = 0; j < Start.getGui().getAminoPanel().getComponentCount(); j++) {
                if (j >= i && j < end) {
                    Start.getGui().getPanelTop().getComponent(j).setForeground(Color.RED);
                } else {
                    Start.getGui().getPanelTop().getComponent(j).setForeground(Color.WHITE);
                }
            }

        }
    }
}
