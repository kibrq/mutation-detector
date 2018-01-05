package Start;

import ActionListeners.CreatingCompareMode;
import Database.DataBase;
import GUI.GUI;


import javax.swing.*;

public class Start {
    private static GUI gui = null;
    private static boolean isCompareMode = false;
    private static DataBase db = new DataBase();
    private static int previousList = 0;
    public static JPanel panel1 = null;
    public static JPanel panel2 = null;
    private static boolean isShiftPressed = false;

    public static boolean isCompareMode() {
        return isCompareMode;
    }

    public static void setIsCompareMode(boolean isCompareMode) {
        Start.isCompareMode = isCompareMode;
    }

    public static DataBase getDb() {
        return db;
    }


    public static int getPreviousList() {
        return previousList;
    }

    public static void setPreviousList(int previousList) {
        Start.previousList = previousList;
    }


    public static boolean isShiftPressed() {
        return isShiftPressed;
    }

    public static void setIsShiftPressed(boolean isShiftPressed) {
        Start.isShiftPressed = isShiftPressed;
    }

    public static GUI getGui() {
        return gui;
    }

    public static void main(String[] args) {
        gui = new GUI("main");
        gui.showGui();
        gui.addKeyListener(new CreatingCompareMode());
    }
}
