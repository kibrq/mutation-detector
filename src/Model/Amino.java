package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Amino {
    String title;
    String[] codons;
    double mass;

    public Amino(String title, String[] codons, double mass) {
        this.title = title;
        this.codons = codons;
        this.mass = mass;
    }

    public String getTitle() {
        return title;
    }

    public String[] getCodons() {
        return codons;
    }

    public double getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return title;
    }
}
