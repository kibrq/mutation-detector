package MyComponents;

import javax.swing.*;
import java.awt.*;

public class JAminoButton extends JButton {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.CYAN);
        g2d.setBackground(Color.CYAN);
        g2d.rotate(2);

    }
}
