package ActionListeners;

import GUI.GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMenu implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList tmp = (JList) e.getSource();
        String value = (String) tmp.getSelectedValue();
        switch (value){

        }
    }
}
