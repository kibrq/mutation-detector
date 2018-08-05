package Listeners.ActionListners.SubmitButton;

import Global.Variables;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreatingAminos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Variables.isSubmitted()) {
            GenerateAminos.generate();
        } else {
            Clear.clear();
        }

    }
}
