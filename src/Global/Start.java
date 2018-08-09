package Global;

import GUI.*;
import Listeners.ActionListners.MenuBarAminosActionListener;
import Listeners.ActionListners.SelectPanel;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Start {
    public static JFrame frame = null;
    public static ArrayList<String> input = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.in"));
        JFrame mainF = new JFrame("Main");
        frame = mainF;
        JMenuBar menuBar = new JMenuBar();
        JButton aminos = new JButton("Aminos");
        JMenu help = new JMenu("Help");

        while (sc.hasNext()) {
            input.add(sc.nextLine().toUpperCase());
        }

        aminos.addActionListener(new MenuBarAminosActionListener());

        JMenuItem item = new JMenuItem("Help");
        item.addActionListener(e -> {
            JPopupMenu popupMenu = new JPopupMenu();
            JLabel label1 = new JLabel("You can select aminoacid by selecting one of them in 'amino' menu");
            JLabel label2 = new JLabel("If you want to see all codons of some amino, just get into a normal mode ");
            JLabel label3 = new JLabel("If you want to compare codons of some two aminos, get into compare mode(shift+f1)");
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

    }
}
