package Listeners.ActionListners;

import GUI.GUI;
import Global.Start;
import Global.Variables;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PeptideInSearchListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuBarAminosActionListener.dialog.setVisible(false);
        JPanel label = (JPanel) e.getSource();
        String arr[] = label.getName().split("\\s+");
        GUI gui = new GUI();
        gui.getTf().setText(arr[0]);
        gui.getInputDM().setText(arr[1]);
        Start.frame.getContentPane().removeAll();
        Start.frame.getContentPane().add(gui);
        Start.frame.repaint();
        Start.frame.revalidate();

        Variables.setGui(gui);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
