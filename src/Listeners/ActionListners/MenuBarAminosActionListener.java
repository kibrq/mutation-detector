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
    private static JPanel leftPanel = new JPanel();
    private static JPanel rightPanel = new JPanel();
    public static JDialog dialog;

    public static void removeAll() {
        leftPanel.removeAll();
        rightPanel.removeAll();
    }

    public static JPanel getLeftPanel() {
        return leftPanel;
    }

    public static JPanel getRightPanel() {
        return rightPanel;
    }

    public static void rePaintValidatePanels() {
        leftPanel.revalidate();
        leftPanel.repaint();
        rightPanel.repaint();
        rightPanel.revalidate();
    }

    public static int getWidth() {
        return leftPanel.getWidth();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int width = Start.frame.getWidth() / 2;
        int height = Start.frame.getHeight() / 2;
        dialog = new JDialog();
        JTextField textField = new JTextField(15);

        textField.setText(Variables.getPreviousStringInSearchTextField());

        textField.addCaretListener(new FindAmino());
        FlowLayout gridLayout = new FlowLayout(FlowLayout.CENTER, 1, 1);

        dialog.setLayout(gridLayout);
        dialog.setSize(new Dimension(width, height));
        dialog.setLocationRelativeTo(null);

        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(width, height / 9));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelTop.add(new JLabel("Enter prefix/slice of sequence...."));
        panelTop.add(textField);

        dialog.add(panelTop);

        JPanel panelLabels = new JPanel();
        panelLabels.setPreferredSize(new Dimension(width, height / 15));
        panelLabels.setLayout(new FlowLayout(FlowLayout.CENTER, 3 * width / 7, 1));
        panelLabels.add(new JLabel("Prefix"));
        panelLabels.add(new JLabel("Slice"));

        dialog.add(panelLabels);


        JSplitPane panelMiddle = new JSplitPane();


        panelMiddle.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        panelMiddle.setPreferredSize(new Dimension(width - 20, height - panelTop.getHeight() - panelLabels.getHeight() - 50));


        JScrollPane scrollPaneLeft = new JScrollPane(leftPanel);
        JScrollPane scrollPaneRight = new JScrollPane(rightPanel);

        scrollPaneLeft.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneRight.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRight.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        GridLayout gl = new GridLayout();
        gl.setRows(1000);
        gl.setColumns(0);
        gl.setVgap(10);

        leftPanel.setLayout(gl);
        rightPanel.setLayout(gl);



        panelMiddle.setLeftComponent(scrollPaneLeft);
        panelMiddle.setRightComponent(scrollPaneRight);


        dialog.add(panelMiddle);


        dialog.setVisible(true);
    }
}
