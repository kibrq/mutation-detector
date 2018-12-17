package Listeners.ActionListners;

import AdditionalClasses.UsefullFunctions;
import Global.Variables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HelpDialog implements ActionListener {
    private Dialog dialog;

    public HelpDialog() {
        dialog = new Dialog(setHM());
    }

    class TabWidget implements MouseListener {

        private JPanel getSource(MouseEvent e) {
            return (JPanel) e.getSource();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            dialog.setDescr(getSource(e).getName());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            getSource(e).setBackground(Variables.getColorActive());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            getSource(e).setBackground(Variables.getColorNonActive());
        }
    }


    class Dialog extends JDialog {
        private JPanel navigationPanel = new JPanel();
        private JPanel panelCenter = new JPanel();
        private ArrayList<JLabel> descr = new ArrayList<>();
        private HashMap<String, String> hm;

        public Dialog(HashMap<String, String> hm) {
            this.hm = hm;
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(new Dimension(d.width / 2, d.height / 2));
            this.setIconImage(Variables.getIcon());
            this.setLocationRelativeTo(null);
            navigationPanel.setBackground(Variables.getColorNonActive());
            navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
            UsefullFunctions.setNormalSize(navigationPanel, new Dimension(this.getWidth() / 6, this.getHeight()));
            for (String s : hm.keySet()) {
                JPanel panel = new JPanel();
                UsefullFunctions.setNormalSize(panel, new Dimension(this.getWidth() / 6, 30));
                panel.setName(s);
                JLabel label = new JLabel();
                label.setFont(Variables.getFontForNavigationPanel());
                label.setText(s);
                panel.add(label);
                panel.addMouseListener(new TabWidget());
                panel.setBackground(Variables.getColorNonActive());
                navigationPanel.add(panel);
            }
            panelCenter.setBackground(Variables.getColorOfRightPanel());
            panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
            this.getContentPane().setLayout(new BorderLayout());
            this.getContentPane().add(navigationPanel, BorderLayout.WEST);
            this.getContentPane().add(panelCenter, BorderLayout.CENTER);

        }

        private void setDescr(String name) {
            descr = new ArrayList<>();
            panelCenter.removeAll();
            descr.add(new JLabel());
            int cur = 0;
            String s = hm.get(name);
            int previous = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\n') {
                    descr.get(cur).setFont(Variables.getFontForDescriptions());
                    descr.get(cur).setText(s.substring(previous, i));
                    previous = i;
                    descr.add(new JLabel());
                    cur++;
                }
            }
            descr.get(cur).setFont(Variables.getFontForDescriptions());
            descr.get(cur).setText(s.substring(previous));

            for (JLabel lb : descr) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panel.setBackground(Variables.getColorOfRightPanel());
                UsefullFunctions.setNormalSize(panel, new Dimension(5 * dialog.getWidth() / 6, 28));
                panel.add(lb);
                panelCenter.add(panel);
            }

            this.revalidate();
            this.repaint();
        }

        private void setToBegin() {
            panelCenter.removeAll();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.setToBegin();

        dialog.setVisible(true);
    }

    private HashMap<String, String> setHM() {
        HashMap<String, String> hm = new HashMap<>();
        String name = "Mode";
        String description = "LLLadlasfjaksfjfjlsf";
        hm.put(name, description);
        name = "Prefix/Suffix";
        description = "ljkgjogjqowejgowjg";
        hm.put(name, description);
        name = "Mass difference";
        description = "KSHFWHFWHFIFHOSFJOJFOWJFOWNFOW\nkgwkegiweghiegjqoegjqoejoqejgoegjemfqeveoneog";
        hm.put(name, description);

        return hm;
    }
}
