package ActionListeners;

import Start.*;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Scrolling implements AdjustmentListener {
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (Start.isSubmitted()) {
            for (int i = 0; i < Start.getGui().getPanelTop().getComponentCount(); i++) {
                if(i>=e.getValue()/60&&i<e.getValue()/60+4){
                    Start.getGui().getPanelTop().getComponents()[i].setForeground(Color.RED);
                }else {
                    Start.getGui().getPanelTop().getComponents()[i].setForeground(Color.WHITE);
                }
            }
        }

    }
}
