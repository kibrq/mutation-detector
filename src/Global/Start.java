package Global;

import AdditionalClasses.UsefullFunctions;
import GUI.*;
import Listeners.ActionListners.MenuBarAminosActionListener;
import Model.AminoAcid;
import Model.Mode;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Start {
    public static JFrame frame = null;
    public static ArrayList<String> input = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.in"));
        AminoAcid.setData();
        JFrame mainF = new JFrame(Variables.getFrameName());
        frame = mainF;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JButton aminos = new JButton("Peptides");
        Object action1 = "my action1";
        Object action2 = "my action2";
        Object action3 = "prefix";
        Object action4 = "suffix";
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_DOWN_MASK), action1);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_DOWN_MASK), action2);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), action3);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), action4);
        panel.getActionMap().put(action1, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui = Variables.getGui();
                if (gui != null) {
                    Variables.clearPanelsWithModCod();
                    Variables.setMode(Mode.COMPARE);
                    Variables.getGui().getSuffix().setVisible(false);
                    Variables.getGui().getPrefix().setVisible(false);
                }
            }
        });
        panel.getActionMap().put(action2, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI gui = Variables.getGui();
                if (gui != null) {
                    Variables.clearPanelsWithModCod();
                    Variables.setMode(Mode.MASS_DIFFERENCE);
                    Variables.getGui().getSuffix().setVisible(Variables.getMode() == Mode.MASS_DIFFERENCE);
                    Variables.getGui().getPrefix().setVisible(Variables.getMode() == Mode.MASS_DIFFERENCE);
                }
            }
        });
        panel.getActionMap().put(action3, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsefullFunctions.prefixAction();
            }
        });
        panel.getActionMap().put(action4, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsefullFunctions.suffixAction();
            }
        });

        panel.setBackground(Variables.getColorOfPanelWithCodonsModifications());

        frame.setContentPane(panel);

        JMenu help = new JMenu("Help");

        while (sc.hasNext()) {
            input.add(sc.nextLine().toUpperCase());
        }

        aminos.addActionListener(new MenuBarAminosActionListener());

        JMenuItem item = new JMenuItem("Help");
        item.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();
            JLabel label1 = new JLabel("You can select peptide by selecting one of them in 'peptide' menu");
            JLabel label2 = new JLabel("If you want to see all codons of some amino acid, just get into a normal mode");
            JLabel label3 = new JLabel("If you want to compare codons of two amino acids, get into compare mode(shift+f1)");
            JLabel label4 = new JLabel("If you want to see possible substitution get into massDifference mode(shift+f2). In this mode you can select prefix(where you think sub might occur) by holding P and click on end of this prefix");
            JLabel label5 = new JLabel("You can do same thing with suffix but you have to hold S instead of P");
            JLabel label6 = new JLabel("If you want to get into normal mode, you have to press combination of keys which you have pressed last");
            popupMenu.add(label1);
            popupMenu.add(label2);
            popupMenu.add(label3);
            popupMenu.add(label4);
            popupMenu.add(label5);
            popupMenu.add(label6);
            popupMenu.show(menuBar, 10, 50);


        });
        help.add(item);
        mainF.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainF.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        menuBar.add(aminos);
        menuBar.add(help);
        mainF.setJMenuBar(menuBar);
        mainF.setVisible(true);
        UsefullFunctions.parseModification();

    }
}
