
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JButton but = new JButton("submit");
    private JTextField tf = new JTextField(50);
    private JPanel panelRight = new JPanel();
    private JPanel panelLeft = new JPanel();
    private JPanel panelTop = new JPanel();
    private JPanel panelMiddle = new JPanel();
    private JPanel panelBottom = new JPanel();
    private JMenu menu = new JMenu();

    public Main(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
        addPanelLeft();
        addingCompsRight();
        addPanelRight();
        this.setVisible(true);
    }

    private void addPanelLeft() {
        panelLeft.add(menu);
        this.getContentPane().add(panelLeft);
    }

    private void addPanelRight() {
        panelRight.setLayout(new GridLayout(3, 1));
        panelRight.add(panelTop);
        panelRight.add(panelMiddle);
        panelRight.add(panelBottom);
        this.getContentPane().add(panelRight);
    }

    private void addingCompsRight() {
        panelTop.setLayout(new FlowLayout());
        panelTop.add(tf);
        panelBottom.setLayout(new FlowLayout());
        panelBottom.add(but);
    }

    public static void main(String[] args) {
        new Main("Main");
    }
}
