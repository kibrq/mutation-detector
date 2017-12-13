package ActionListeners;

import Database.DataBase;
import GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreatingCodons implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String key=e.getActionCommand();
        JPopupMenu pop=new JPopupMenu();
        DataBase db=new DataBase();
        ArrayList<String> list=db.getDatabase().get(key);
        if(list==null){
            return;
        }
        JPanel panel = new JPanel(new GridLayout(list.size(),1 , 0 ,10));
        for (int i = 0; i <list.size(); i++) {
            JLabel label=new JLabel(list.get(i));
            panel.add(label);
        }
        pop.add(panel);
        pop.show(GUI.panelBottom, 0, 0);
    }
}
