package Global;

import AdditionalClasses.UsefullFunctions;
import Listeners.ActionListners.HelpDialog;
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
        frame = new JFrame(Variables.getFrameName());

        frame.setIconImage(Variables.getIcon());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JButton aminos = new JButton("Peptides");
        Object action1 = "my action1";
        Object action3 = "prefix";
        Object action4 = "suffix";
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_DOWN_MASK), action1);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), action3);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), action4);
        panel.getActionMap().put(action1, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Variables.setMode(Mode.MASS_DIFFERENCE);
            }
        });
        panel.getActionMap().put(action3, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Variables.getMode() == Mode.MASS_DIFFERENCE)
                    UsefullFunctions.prefixAction();
            }
        });
        panel.getActionMap().put(action4, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Variables.getMode() == Mode.MASS_DIFFERENCE)
                    UsefullFunctions.suffixAction();
            }
        });

        panel.setBackground(Variables.getColorOfPanelWithCodonsModifications());

        frame.setContentPane(panel);


        while (sc.hasNext()) {
            input.add(sc.nextLine().toUpperCase());
        }

        aminos.addActionListener(new MenuBarAminosActionListener());
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(new HelpDialog());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        menuBar.add(aminos);
        menuBar.add(helpButton);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        UsefullFunctions.parseModification();

    }
}
