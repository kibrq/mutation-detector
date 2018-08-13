package Model;

import Database.DataBase;
import Global.Variables;

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
    public Amino(String title){
        DataBase db = Variables.getDb();
        for(Amino a : db.getDatabase()){
            if(a.title.equals(title)){
                this.title = a.title;
                this.codons = a.codons;
                this.mass = a.mass;
                return;
            }
        }
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

    @Override
    public boolean equals(Object obj) {
        return this.title.equals(((Amino)obj).title);
    }
}
