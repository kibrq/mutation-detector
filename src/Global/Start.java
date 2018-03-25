package Global;

import GUI.*;
import Listeners.KeyListeners.CreatingModes;

public class Start {
    private static GUI gui = null;

    public static void main(String[] args) {
        gui = new GUI("Main");
        gui.addKeyListener(new CreatingModes());
        Variables.setGui(gui);
    }
}
