package MyComponents;

import Global.Variables;
import Model.AminoAcid;
import Model.Mode;
import Model.Modification;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JModificationPanel extends JPanel {
    private AminoAcid a;
    private int num;
    private ArrayList<Modification> mods;

    public JModificationPanel(int num) {
        a = Variables.getSeq()[num];
        mods = Variables.getMode() == Mode.MASS_DIFFERENCE ? Variables.getTmpModifications().get(num) : Variables.getModificationDataBase().get(a);
        this.num = num;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int midX = this.getWidth() / 2;
        String s = "Possible modifications";
        int x = (int) (midX - s.length() * 4.2);
        int y = 30;
        g2d.setFont(Variables.getFontForSubTitles());
        g2d.drawString(s, x, y);
        if (mods.isEmpty()) {
            g2d.setFont(Variables.getFontForTitles());
            s = "NONE";
            x = midX - s.length() * 4;
            y += 100;
            g2d.drawString(s, x, y);
        } else {
            int i = 1;
            g2d.setFont(Variables.getFontForLittleSignings());
            for (Modification mod : mods) {
                s = i + ". " + mod.getName() + " (" + mod.getMassDifference() + "Da)";
                x = 30;
                y += 30;
                g2d.drawString(s, x, y);
                i++;
            }
        }
    }
}
