package MyComponents;

import Global.Variables;
import Model.AminoAcid;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JAminoAcidChooser extends JComponent {
    private SwitchButton left = new SwitchButton(true);
    private int currentNumOfAcid = 0;
    private JLabel aminoAcid = new JLabel("A");
    private SwitchButton right = new SwitchButton(false);
    private JPanel root = new JPanel();
    private JPanel panel = new JPanel();

    public JAminoAcidChooser(Dimension d) {
        root.setSize(d);
        root.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
        this.setBackground(Variables.getColorOfRightPanel());
        left.setSize(new Dimension(d.width / 3, d.height));
        right.setSize(new Dimension(d.width / 3, d.height));
        aminoAcid.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 4 * d.height / 5));
        panel.setPreferredSize(new Dimension(d.width / 3, d.height));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        panel.add(aminoAcid);
        root.add(left);
        root.add(panel);
        root.add(right);
    }

    @Override
    public void setBackground(Color background) {
        root.setBackground(background);
        right.setBackground(background);
        left.setBackground(background);
        aminoAcid.setBackground(background);
        panel.setBackground(background);
    }
    public AminoAcid getAminoAcid(){
        return AminoAcid.valueOf(aminoAcid.getText());
    }

    public JPanel getRoot() {
        return root;
    }

    public void addValueChangeListener(ActionListener l){
        left.addActionListener(l);
        right.addActionListener(l);
    }

    @Override
    public void setSize(Dimension d) {
        root.setMinimumSize(d);
        root.setMaximumSize(d);
        root.setSize(d);
    }

    class SwitchButton extends JButton {
        private boolean left;
        private int bold = 1;

        public SwitchButton(boolean left) {
            super();
            this.left = left;
            this.repaint();
            this.addActionListener(e -> {
                if (left) {
                    currentNumOfAcid--;
                    if (currentNumOfAcid < 0) {
                        currentNumOfAcid = 19;
                    }
                } else {
                    currentNumOfAcid++;
                    if (currentNumOfAcid > 19) {
                        currentNumOfAcid = 0;
                    }
                }
                aminoAcid.setText(AminoAcid.values()[currentNumOfAcid].getTitle());
                root.repaint();
            });
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int startX = left ? this.getWidth() - 2 : 2;
            for (int i = 1; i <= bold; i++) {
                g2d.drawLine(startX, 2 + i, this.getWidth() - startX, (this.getHeight() - bold) / 2 + i);
                g2d.drawLine(this.getWidth() - startX, (this.getHeight() - bold) / 2 + i, startX, this.getHeight() - bold + i - 2);
            }
        }

        @Override
        public void setSize(Dimension d) {
            this.setMaximumSize(d);
            this.setMinimumSize(d);
            this.setPreferredSize(d);
            this.repaint();
        }

    }

    private void addToCenter(JPanel pan, JComponent c) {
        JPanel panel = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(c);
        pan.add(panel);
    }

}
