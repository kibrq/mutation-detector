package MyComponents;

import AdditionalClasses.UsefullFunctions;
import Global.Variables;
import Listeners.ActionListners.AminoButton.AminoAcidClicked;
import Model.AminoAcid;
import Model.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JSubstitutionsPanel extends JPanel {
    private AminoAcid a;
    private ArrayList<AminoAcid> candidates;
    private int num;
    private int currentCandidate = 0;


    public JSubstitutionsPanel(int num) {
        this.a = Variables.getSeq()[num];
        this.num = num;
        this.setLayout(new BorderLayout());
        JPanel navigationPanel = new JPanel();
        if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
            if (Variables.getCandidates().get(num).size() > 1) {
                JButton butNext = new JButton("NEXT");
                butNext.addActionListener(e -> {
                    currentCandidate++;
                    currentCandidate %= candidates.size();
                    this.repaint();
                    this.revalidate();
                });
                navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                navigationPanel.add(butNext);
            }
        }
        this.add(navigationPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int midX = this.getWidth() / 2;
        g2d.setFont(Variables.getFontForSubTitles());
        String s = "Substitutions";
        int x = midX - (int) (s.length() * 3.5);
        int y = 30;
        if (Variables.getMode() == Mode.MASS_DIFFERENCE) {
            g2d.drawString(s, x, y);
            int margin = 100;
            candidates = Variables.getCandidates().get(num);
            if (candidates.isEmpty()) {
                g2d.setFont(Variables.getFontForTitles());
                s = "NONE";
                x = midX - (int) (s.length() * 5);
                y += 100;
                g2d.drawString(s, x, y);
            } else {
                AminoAcid a2 = candidates.get(currentCandidate);
                s = a.getTitle();
                x = margin;
                y += 50;
                g2d.drawString(s, x, y);
                s = a2.getTitle();
                x = this.getWidth() - margin - s.length() * 3;
                g2d.drawString(s, x, y);
                g2d.setFont(Variables.getFontForCodons());
                int startY = y;
                x = margin;
                for (String cod : a.getCodons()) {
                    s = cod;
                    x = (margin - s.length() * 4);
                    y += 30;
                    g2d.drawString(s, x, y);
                }
                y = startY;
                for (String cod : a2.getCodons()) {
                    s = cod;
                    x = (this.getWidth() - margin - s.length() * 4);
                    y += 30;
                    g2d.drawString(s, x, y);
                }

                Color[] colors = {new Color(200, 0, 0), new Color(0, 200, 0), new Color(0, 0, 200), new Color(100, 0, 0), new Color(0, 100, 0), new Color(0, 0, 100), new Color(100, 20, 100), new Color(20, 100, 100)};

                int num = 0;
                startY += Variables.getFontForCodons().getSize();
                for (int i = 0; i < a.getCodons().length; i++) {
                    for (int j = 0; j < a2.getCodons().length; j++) {
                        if (AminoAcidClicked.isSMP(a.getCodons()[i], a2.getCodons()[j])) {
                            g2d.setColor(colors[(num) % colors.length]);
                            g2d.drawLine(margin + a.getCodons()[i].length() * 8 + 5, startY + i * 30, this.getWidth() - margin - a2.getCodons()[j].length() * 4, startY + j * 30);
                            num++;
                        }
                    }
                }
                g2d.setColor(Color.BLACK);
                y = startY;
                y += Math.max(a.getCodons().length, a2.getCodons().length) * 50;
                s = "Mass difference is " + UsefullFunctions.round(a.getMass() - a2.getMass()) + "Da";
                g2d.setFont(Variables.getFontForLittleSignings());
                x = (int) (midX - s.length() * 4.5);
                g2d.drawString(s, x, y);
            }
        } else {
            s = "Encoding codons";
            x = (int) (midX - s.length() * 4.2);
            g2d.drawString(s, x, y);
            g2d.setFont(Variables.getFontForCodons());
            for (String cod : a.getCodons()) {
                s = cod;
                x = midX - s.length() * 4;
                y += 30;
                g2d.drawString(s, x, y);

            }

        }

    }
}
