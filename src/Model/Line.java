package Model;

import java.awt.*;

public class Line {
    MyPoint a, b;
    Color color;

    public Line(MyPoint a, MyPoint b, Color color) {
        this.a = a;
        this.b = b;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public MyPoint getA() {
        return a;
    }

    public void setA(MyPoint a) {
        this.a = a;
    }

    public MyPoint getB() {
        return b;
    }

    public void setB(MyPoint b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return a.toString()+" "+b.toString()+" "+color.toString();
    }
}
