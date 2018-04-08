package Listeners.ActionListners;

import GUI.GUI;
import Global.Start;
import Global.Variables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem t = (JMenuItem) e.getSource();
        String[] tmp = t.getName().split("\\s+");
        GUI gui = new GUI();
        gui.getTf().setText(tmp[0]);
        gui.getInputDM().setText(tmp[1]);
        Variables.setGui(gui);
        Start.frame.getContentPane().removeAll();
        Start.frame.getContentPane().add(gui);
        Start.frame.getContentPane().repaint();
        Start.frame.getContentPane().revalidate();
    }
}
