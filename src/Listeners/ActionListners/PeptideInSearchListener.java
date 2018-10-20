package Listeners.ActionListners;

import AdditionalClasses.UsefullFunctions;
import GUI.GUI;
import Global.Start;
import Global.Variables;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PeptideInSearchListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        MenuBarAminosActionListener.dialog.setVisible(false);
        JPanel label = (JPanel) e.getSource();
        String arr[] = label.getName().split("\\s+");
        GUI gui = new GUI();
        double a = UsefullFunctions.round(Double.parseDouble(arr[1]));

        Variables.setToBegin();

        Variables.setDm(a);
        Variables.setPpm(10);
        Variables.setSeq(arr[0]);

        gui.getAminoSequence().setName(arr[0]);
        gui.getMistakePPM().setText("10");
        gui.getMassDifference().setName(Double.toString(a));
        Start.frame.getContentPane().removeAll();
        Variables.setGui(gui);
        gui.fillWithData();
        Start.frame.setName("Main");
        Start.frame.getContentPane().add(gui, BorderLayout.CENTER);
        Start.frame.repaint();
        Start.frame.revalidate();

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
