package Global;


import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Model.AminoAcid;
import Model.Mode;
import Model.Modification;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Variables {
    private final static String FRAME_NAME = "MutationDetector";
    private final static String NORMAL_MODE = "Viewing of codons";
    private final static String COMPARE_MODE = "Comparing amino acids";
    private final static String MASS_DIFFERENCE_MODE = "Detecting substitutions";

    //Colors
    private final static Color normalColor = new Color(0, 0, 0);
    private final static Color colorOfMD = new Color(10, 10, 220);
    private final static Color colorInViewOfScroll = new Color(220, 20, 40);
    private final static Color colorOfMod = new Color(20, 200, 50);
    private final static Color colorOfBoth = new Color(200, 200, 50);
    private final static Color colorOfCheckedPrefix_Suffix = new Color(197, 146, 152);

    private final static Color colorOfRightPanel = new Color(245, 223, 209);
    private final static Color colorOfPanelWithSequnece = new Color(216, 194, 180);
    private final static Color colorOfPanelWithCodonsModifications = new Color(232, 210, 196);

    private final static Font fontForTitles = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    private final static Font fontForLittleSignings = new Font(Font.SANS_SERIF, Font.PLAIN, 20);


    private static String previousStringInSearchTextField = "";

    private static HashMap<AminoAcid, ArrayList<Modification>> modificationDataBase = new HashMap<>();

    private static int currentCandidate = 0;
    private static HashMap<AminoAcid, ArrayList<AminoAcid>> candidates = new HashMap<>();
    private static AminoAcid currentAmino = null;

    private static Mode mode = Mode.NORMAL;

    private static GUI gui = null;
    private static AminoAcid seq[];
    private static double dm = 0;
    private static double ppm = 0;
    private static double currentMistake = 0;
    private static AminoAcid a1 = null;
    private static AminoAcid a2 = null;
    final private static int countInScrollPanel = 10;
    private static double massesPrefix[];
    final static private int buttonWidth = 45;
    private static boolean prefixSelecting = false;
    private static boolean suffixSelecting = false;
    private static HashMap<AminoAcid, ArrayList<Modification>> tmpModifications = new HashMap<>();

    public static String getFrameName() {
        return FRAME_NAME;
    }

    public static String getNormalMode() {
        return NORMAL_MODE;
    }

    public static String getCompareMode() {
        return COMPARE_MODE;
    }

    public static String getMassDifferenceMode() {
        return MASS_DIFFERENCE_MODE;
    }


    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        if (mode == Variables.mode) {
            Variables.mode = Mode.NORMAL;
        } else {
            Variables.mode = mode;
        }
        switch (Variables.mode) {
            case NORMAL:
                gui.getCurrentMode().setText(NORMAL_MODE);
                break;
            case COMPARE:
                gui.getCurrentMode().setText(COMPARE_MODE);
                break;
            case MASS_DIFFERENCE:
                gui.getCurrentMode().setText(MASS_DIFFERENCE_MODE);
        }
        for (Component component : gui.getPanelWithAminoButtons().getComponents()) {
            component.setEnabled(Variables.mode != Mode.MASS_DIFFERENCE);
        }
        clearPanelsWithModCod();

    }

    public static void clearPanelsWithModCod() {
        a1 = null;
        a2 = null;
        candidates = null;
        tmpModifications = null;
        gui.getPanelWithModifications().removeAll();
        gui.getPanelWithCodons().removeAll();
        UsefullFunctions.revalidateRepaint(gui.getPanelWithModifications());
        UsefullFunctions.revalidateRepaint(gui.getPanelWithCodons());
    }

    public static AminoAcid getCurrentAmino() {
        return currentAmino;
    }

    public static void setCurrentAmino(AminoAcid currentAmino) {
        Variables.currentAmino = currentAmino;
    }

    public static double getPpm() {
        return ppm;
    }

    public static void setPpm(double ppm) {
        Variables.ppm = ppm;
    }

    public static double getDm() {
        return dm;
    }


    public static void setDm(double dm) {
        Variables.dm = dm;
    }

    public static double getCurrentMistake() {
        return currentMistake;
    }

    public static void setCurrentMistake(double currentMistake) {
        currentMistake = UsefullFunctions.round(currentMistake);
        Variables.currentMistake = currentMistake;
        Variables.getGui().getCurrentMistake().setText(Double.toString(currentMistake) + "Da");
    }


    public static Font getFontForTitles() {
        return fontForTitles;
    }


    public static Font getFontForLittleSignings() {
        return fontForLittleSignings;
    }

    public static HashMap<AminoAcid, ArrayList<Modification>> getTmpModifications() {
        return tmpModifications;
    }

    public static void setTmpModifications(HashMap<AminoAcid, ArrayList<Modification>> tmpModifications) {
        Variables.tmpModifications = tmpModifications;
    }

    public static String getPreviousStringInSearchTextField() {
        return previousStringInSearchTextField;
    }

    public static void setPreviousStringInSearchTextField(String previousStringInSearchTextField) {
        Variables.previousStringInSearchTextField = previousStringInSearchTextField;
    }

    public static HashMap<AminoAcid, ArrayList<Modification>> getModificationDataBase() {
        return modificationDataBase;
    }

    public static void setModificationDataBase(HashMap<AminoAcid, ArrayList<Modification>> modificationDataBase) {
        Variables.modificationDataBase = modificationDataBase;
    }

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
        if (currentCandidate != 0) {
            if (currentCandidate == Variables.getCandidates().get(Variables.getCurrentAmino()).size()) {
                currentCandidate = 0;
            }
        }
        Variables.currentCandidate = currentCandidate;
    }

    public static void setBeginToModifications() {
        for (AminoAcid a : AminoAcid.values()
        ) {
            modificationDataBase.put(a, new ArrayList<>());
        }
    }


    public static GUI getGui() {
        return gui;
    }


    public static Color getNormalColor() {
        return normalColor;
    }

    public static Color getColorOfMut() {
        return colorOfMD;
    }

    public static Color getColorInViewOfScroll() {
        return colorInViewOfScroll;
    }

    public static Color getColorOfMod() {
        return colorOfMod;
    }

    public static Color getColorOfBoth() {
        return colorOfBoth;
    }


    public static AminoAcid getA1() {
        return a1;
    }

    public static AminoAcid getA2() {
        return a2;
    }

    public static int getCountInScrollPanel() {
        return countInScrollPanel;
    }

    public static double[] getMassesPrefix() {
        return massesPrefix;
    }

    public static AminoAcid[] getSeq() {
        return seq;
    }

    public static int getButtonWidth() {
        return buttonWidth;
    }


    public static void setGui(GUI gui) {
        Variables.gui = gui;
    }


    public static void setA1(AminoAcid a1) {
        Variables.a1 = a1;
    }

    public static void setA2(AminoAcid a2) {
        Variables.a2 = a2;
    }

    public static void setMassesPrefix(double[] massesPrefix) {
        Variables.massesPrefix = massesPrefix;
    }

    public static void setSeq(String s) {
        seq = new AminoAcid[s.length()];
        int i = 0;
        for (Character c : s.toCharArray()) {
            seq[i] = AminoAcid.valueOf(Character.toString(c));
            i++;
        }
    }


    public static HashMap<AminoAcid, ArrayList<AminoAcid>> getCandidates() {
        return candidates;
    }

    public static void setCandidates(HashMap<AminoAcid, ArrayList<AminoAcid>> candidates) {
        Variables.candidates = candidates;
    }

    public static void setToBegin() {
        currentAmino = null;
        currentCandidate = 0;
        currentMistake = 0;
        candidates = null;
        prefixSelecting = false;
        suffixSelecting = false;
        a1 = null;
        a2 = null;
        massesPrefix = null;
    }

    public static boolean isPrefixSelecting() {
        return prefixSelecting;
    }

    public static void setPrefixSelecting(boolean prefixSelcting) {
        Variables.prefixSelecting = prefixSelcting;
        for (Component component : gui.getPanelWithAminoButtons().getComponents()) {
            component.setEnabled(Variables.isPrefixSelecting());
        }

    }

    public static boolean isSuffixSelecting() {
        return suffixSelecting;
    }

    public static void setSuffixSelecting(boolean suffixSelecting) {
        Variables.suffixSelecting = suffixSelecting;
        for (Component component : gui.getPanelWithAminoButtons().getComponents()) {
            component.setEnabled(Variables.isSuffixSelecting());
        }
    }

    public static Color getColorOfRightPanel() {
        return colorOfRightPanel;
    }

    public static Color getColorOfPanelWithSequnece() {
        return colorOfPanelWithSequnece;
    }

    public static Color getColorOfPanelWithCodonsModifications() {
        return colorOfPanelWithCodonsModifications;
    }

    public static Color getColorOfCheckedPrefix_Suffix() {
        return colorOfCheckedPrefix_Suffix;
    }
}
