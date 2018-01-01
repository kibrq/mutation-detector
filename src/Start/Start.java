package Start;

import ActionListeners.CreatingCompareMode;
import Database.DataBase;
import GUI.GUI;


import javax.swing.*;

public class Start {
    public static GUI gui = null;
    public static boolean isCompareMode = false;
    public static DataBase db = new DataBase();
    public static int previousList = 0;
    public static JPanel panel1 = null;
    public static JPanel panel2 = null;

    public static void main(String[] args) {
        gui = new GUI("main");
        gui.showGui();
        gui.addKeyListener(new CreatingCompareMode());
    }
}
