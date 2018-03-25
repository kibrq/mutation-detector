package Listeners.ActionListners.SubmitButton;

import GUI.GUI;
import Global.Variables;
import Listeners.ActionListners.AminoButton.Help;


public class Clear {
    public static void clear() {
        GUI gui = Variables.getGui();
        gui.settingPanelTop();
        Variables.setSubmitted(false);
        gui.getBut().setText("Submit");
        gui.getPanelTop().repaint();
        if (Variables.panel1 != null) {
            Variables.panel1.setVisible(false);
            Variables.panel1 = null;
        }
        if (Variables.panel2 != null) {
            Variables.panel2.setVisible(false);
            Variables.panel2 = null;
        }
        Help.removeAllFromUnderBottomPanel();
        gui.getTf().setFocusable(true);
    }
}
