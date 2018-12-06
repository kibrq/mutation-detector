package MyComponents;

import javax.swing.*;
import java.awt.*;

public class JAminoButton extends JButton {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if(!this.isEnabled()){
            g2d.setColor(Color.RED);
            g2d.drawLine(0,this.getHeight(), this.getWidth(), 0);
            g2d.drawLine(0,0,this.getWidth(),this.getHeight());
        }else{
            g2d.setColor(Color.CYAN);
            g2d.setBackground(Color.CYAN);
            g2d.rotate(2);
        }
    }
}
