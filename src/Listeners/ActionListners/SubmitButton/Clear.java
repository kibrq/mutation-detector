package Listeners.ActionListners.SubmitButton;

import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Global.Variables;


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
        UsefullFunctions.removeAllFromUnderBottomPanel();
        gui.getTf().setFocusable(true);
    }
}
