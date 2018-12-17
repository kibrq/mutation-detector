package Listeners.ActionListners;


import Global.Start;
import Global.Variables;
import Listeners.KeyListeners.FindAmino;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MenuBarAminosActionListener implements ActionListener {

    public class Dialog extends JDialog {
        private JPanel leftPanel = new Panel();
        private JPanel rightPanel = new Panel();

        @Override
        public void removeAll() {
            Variables.getDialog().leftPanel.removeAll();
            Variables.getDialog().rightPanel.removeAll();
        }

        private Dialog() {
            int width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
            this.setSize(new Dimension(width, height));
            this.setLocationRelativeTo(null);
            this.setIconImage(Variables.getIcon());
            this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            FlowLayout gridLayout = new FlowLayout(FlowLayout.CENTER, 1, 1);
            this.getContentPane().setLayout(gridLayout);
            JTextField textField = new JTextField(15);
            textField.setText(Variables.getPreviousStringInSearchTextField());
            textField.addCaretListener(new FindAmino());
            FindAmino.sizeOfPanel = new Dimension(width / 2, 20);

            JPanel panelTop = new JPanel();
            panelTop.setPreferredSize(new Dimension(width, height / 9));
            panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            panelTop.add(new JLabel("Enter prefix/slice of sequence...."));
            panelTop.add(textField);

            this.getContentPane().add(panelTop);

            JPanel panelLabels = new JPanel();
            panelLabels.setPreferredSize(new Dimension(width, height / 15));
            panelLabels.setLayout(new FlowLayout(FlowLayout.CENTER, 3 * width / 7, 1));
            panelLabels.add(new JLabel("Prefix"));
            panelLabels.add(new JLabel("Slice"));

            this.getContentPane().add(panelLabels);


            JSplitPane panelMiddle = new JSplitPane();
            panelMiddle.setResizeWeight(0.5);

            panelMiddle.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

            panelMiddle.setPreferredSize(new Dimension(width - 20, height - panelTop.getHeight() - panelLabels.getHeight() - 112));


            JScrollPane scrollPaneLeft = new JScrollPane(leftPanel);
            JScrollPane scrollPaneRight = new JScrollPane(rightPanel);


            scrollPaneLeft.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPaneRight.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPaneLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPaneRight.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


            panelMiddle.setLeftComponent(scrollPaneLeft);
            panelMiddle.setRightComponent(scrollPaneRight);


            this.getContentPane().add(panelMiddle);

        }

    }

    public MenuBarAminosActionListener() {
        Variables.setDialog(new Dialog());
    }

    public static JPanel getLeftPanel() {
        return Variables.getDialog().leftPanel;
    }

    public static JPanel getRightPanel() {
        return Variables.getDialog().rightPanel;
    }

    public static void rePaintValidatePanels() {
        Variables.getDialog().leftPanel.add(Box.createVerticalGlue());
        Variables.getDialog().rightPanel.add(Box.createVerticalGlue());
        Variables.getDialog().leftPanel.revalidate();
        Variables.getDialog().leftPanel.repaint();
        Variables.getDialog().rightPanel.repaint();
        Variables.getDialog().rightPanel.revalidate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Variables.getDialog().removeAll();
        Variables.getDialog().setVisible(true);
    }

    static class Panel extends JPanel {
        public void addComp(Component component) {
            this.add(Box.createVerticalStrut(15));
            this.add(component);
        }
    }
}
