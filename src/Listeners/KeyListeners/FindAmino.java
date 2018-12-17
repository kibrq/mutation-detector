package Listeners.KeyListeners;


import AdditionalClasses.UsefullFunctions;
import Global.Start;
import Global.Variables;
import Listeners.ActionListners.MenuBarAminosActionListener;
import Listeners.ActionListners.PeptideInSearchListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.*;


import java.util.ArrayList;

public class FindAmino implements CaretListener {
    private String s = null;
    public static Dimension sizeOfPanel;

    @Override
    public void caretUpdate(CaretEvent e) {

        Variables.getDialog().removeAll();
        JTextField tf = (JTextField) e.getSource();
        String s = tf.getText();
        s = s.toUpperCase();


        ArrayList<String> in = Start.input;


        if (!s.equals("")) {
            q = remPow(p, s.length(), H);
            long hash = hash(s);
            for (String str :
                    in) {
                String[] arr = str.split("\\s+");
                String md = Double.toString(UsefullFunctions.round(Double.parseDouble(arr[1])));
                int tmp = matches(str, s, hash);
                if (tmp != -1) {
                    JPanel panel1 = new JPanel();
                    panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                    UsefullFunctions.setNormalSize(panel1, sizeOfPanel);
                    int leftEnd = tmp > 4 ? tmp - 4 : 0;
                    int rightEnd = tmp + s.length() < arr[0].length() - 1 - 4 ? tmp + s.length() + 4 : arr[0].length();
                    JLabel label11 = new JLabel(arr[0].substring(leftEnd, tmp));
                    JLabel label12 = new JLabel(arr[0].substring(tmp, tmp + s.length()));
                    label12.setForeground(Color.RED);
                    JLabel label13 = new JLabel(arr[0].substring(tmp + s.length(), rightEnd) + " " + md);
                    panel1.setName(str.toUpperCase());
                    panel1.addMouseListener(new PeptideInSearchListener());
                    panel1.add(label11);
                    panel1.add(label12);
                    panel1.add(label13);
                    MenuBarAminosActionListener.getRightPanel().add(panel1);
                    if (tmp == 0) {
                        JPanel panel2 = new JPanel();
                        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
                        UsefullFunctions.setNormalSize(panel2, sizeOfPanel);
                        JLabel label21 = new JLabel(arr[0].substring(leftEnd, tmp));
                        JLabel label22 = new JLabel(arr[0].substring(tmp, tmp + s.length()));
                        label22.setForeground(Color.RED);
                        JLabel label23 = new JLabel(arr[0].substring(tmp + s.length(), rightEnd) + " " + md);
                        panel2.addMouseListener(new PeptideInSearchListener());
                        panel2.setName(str.toUpperCase());
                        panel2.add(label21);
                        panel2.add(label22);
                        panel2.add(label23);
                        MenuBarAminosActionListener.getLeftPanel().add(panel2);
                    }
                }
            }

        }
        Variables.setPreviousStringInSearchTextField(s);
        MenuBarAminosActionListener.rePaintValidatePanels();

    }

    private long remPow(long p, long M, long H) {
        if (M == 0) {
            return 1;
        }
        if (M % 2 == 1) {
            return remMul(p, remPow(p, M - 1, H), H);
        } else {
            long tmp = remPow(p, M / 2, H);
            return remMul(tmp, tmp, H);
        }
    }

    private int p = 37;
    private int H = Integer.MAX_VALUE;
    private long q = 0;

    private long hash(String s) {
        long hash2 = 0;
        for (int i = 0; i < s.length(); i++) {
            hash2 = (hash2 * p + s.charAt(i)) % H;
        }
        return hash2;
    }

    private int matches(String s1, String s2, long hash) {
        long p = 37;
        long H = Integer.MAX_VALUE;
        long M = s2.length();
        long q = remPow(p, M, H);
        long hash1 = 0;
        for (int i = 0; i < M; i++) {
            hash1 = (hash1 * p + s1.charAt(i)) % H;
        }
        for (int i = 0; i <= s1.length() - M; i++) {
            if (hash1 == hash) {
                boolean flag = true;
                for (int j = i; j < i + M; j++) {
                    if (s1.charAt(j) != s2.charAt(j - (i))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return i;
                }
            }
            if (i != s1.length() - M) {
                hash1 = (remMul(hash1, p, H) - remMul(q, s1.charAt(i), H)
                        + (long) (s1.charAt((int) (i + M))) + H)
                        % H;

            }
        }
        return -1;
    }

    private long remMul(long a, long b, long H) {
        if (b == 1) {
            return a % H;
        }
        if (b % 2 == 1) {
            return (a + remMul(a, b - 1, H)) % H;
        }
        return (2 * remMul(a, b / 2, H)) % H;
    }

}

