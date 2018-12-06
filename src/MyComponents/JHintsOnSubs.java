package MyComponents;

import AdditionalClasses.UsefullFunctions;
import Global.Variables;
import Listeners.ActionListners.AminoButton.AminoAcidClicked;

import javax.swing.*;
import java.awt.*;
import java.time.temporal.ValueRange;

public class JHintsOnSubs extends JPanel {
    private JAminoAcidChooser a1, a2;
    private CodonPanel cp;
    private int hGap, AACWidth;


    public JHintsOnSubs(int hGap, int AACWidth) {
        this.hGap = hGap;
        this.AACWidth = AACWidth;
        a1 = new JAminoAcidChooser(new Dimension(AACWidth, 20));
        a2 = new JAminoAcidChooser(new Dimension(AACWidth, 20));
        cp = new CodonPanel();
        cp.setBackground(Variables.getColorOfRightPanel());
        a1.addValueChangeListener(e ->
                cp.repaint());
        a2.addValueChangeListener(e ->
                cp.repaint());
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, hGap, 0));
        panel1.setBackground(Variables.getColorOfRightPanel());
        panel1.add(a1.getRoot());
        panel1.add(a2.getRoot());
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.NORTH);
        this.add(cp, BorderLayout.CENTER);
        this.setBackground(Variables.getColorOfRightPanel());

    }

    class CodonPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (a1.getAminoAcid() != a2.getAminoAcid()) {
                int x = hGap + AACWidth / 2 - 24;
                Color[] colors = {new Color(100, 200, 150), new Color(230, 200, 150), new Color(10, 130, 5), new Color(200, 10, 150), new Color(100, 200, 150)};
                Graphics2D g2d = (Graphics2D) g;
                for (int i = 0; i < a1.getAminoAcid().getCodons().length; i++) {
                    g2d.drawString(a1.getAminoAcid().getCodons()[i], x, 10 + 20 * (i));
                }
                for (int i = 0; i < a2.getAminoAcid().getCodons().length; i++) {
                    g2d.drawString(a2.getAminoAcid().getCodons()[i], this.getWidth() - x - 24, 10 + 20 * i);
                }
                int count = 0;
                for (int i = 0; i < a1.getAminoAcid().getCodons().length; i++) {
                    for (int j = 0; j < a2.getAminoAcid().getCodons().length; j++) {
                        if (AminoAcidClicked.isSMP(a1.getAminoAcid().getCodons()[i], a2.getAminoAcid().getCodons()[j])) {
                            g2d.setColor(colors[count]);
                            count = (count + 1) % colors.length;
                            g2d.drawLine(x + 24, 10 + 20 * (i - 1) + 15, this.getWidth() - x - 24, 10 + 20 * (j - 1) + 15);
                        }
                    }
                }
                g2d.setColor(Color.BLACK);
                g2d.drawString("Mass Difference: " + UsefullFunctions.round(a1.getAminoAcid().getMass() - a2.getAminoAcid().getMass()) + "Da", x, Math.max(a1.getAminoAcid().getCodons().length, a2.getAminoAcid().getCodons().length) * 20 + 30);

            }
        }
    }
}

