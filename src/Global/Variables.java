package Global;



import Database.DataBase;
import GUI.GUI;
import Model.Amino;


import javax.swing.*;
import java.awt.*;

public class Variables {
    private static String previousStringInSearchTextField = "";

    public static String getPreviousStringInSearchTextField() {
        return previousStringInSearchTextField;
    }

    public static void setPreviousStringInSearchTextField(String previousStringInSearchTextField) {
        Variables.previousStringInSearchTextField = previousStringInSearchTextField;
    }

    private static int currentCandidate = 0;
    private static GUI gui = null;
    private static boolean isCompareMode = false;
    private static DataBase db = new DataBase();
    public static int numSuffix = -1;
    public static double[] massesSuffix;
    public static Color colorOfChoosen = Color.blue;
    public static JPanel panel1 = null;
    public static JPanel panel2 = null;
    private static boolean isShiftPressed = false;
    private static boolean isMassDiff = false;
    private static boolean Submitted = false;
    final public static int countInScrollPanel = 10;
    public static double massesPrefix[];
    public static Amino seq[];
    public static int numPrefix = -1;
    final static public int buttonWidth = 60;
    public static boolean isPrefixSelect = false;
    public static boolean isSuffixSelect = false;


    public static int getCurrentCandidate() {
        return currentCandidate;
    }

    public static void minusCur() {
        currentCandidate--;
    }

    public static void plusCur() {
        currentCandidate++;
    }

    public static void setCurrentCandidate(int currentCandidate) {
        Variables.currentCandidate = currentCandidate;
    }

    public static boolean isSubmitted() {
        return Submitted;
    }

    public static void setSubmitted(boolean submitted) {
        Submitted = submitted;
    }

    public static boolean isCompareMode() {
        return isCompareMode;
    }

    public static boolean isMassDiff() {
        return isMassDiff;
    }

    public static void setIsMassDiff(boolean isMassDiff) {
        Variables.isMassDiff = isMassDiff;
    }

    public static void setIsCompareMode(boolean isCompareMode) {
        Variables.isCompareMode = isCompareMode;
    }

    public static DataBase getDb() {
        return db;
    }






    public static boolean isShiftPressed() {
        return isShiftPressed;
    }

    public static void setIsShiftPressed(boolean isShiftPressed) {
        Variables.isShiftPressed = isShiftPressed;
    }

    public static GUI getGui() {
        return gui;
    }

    public static void setGui(GUI gui1){
        gui = gui1;
    }
}
