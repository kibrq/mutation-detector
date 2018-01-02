package Database;

import Model.Amino;

import java.util.*;

public class DataBase {
    private ArrayList<Amino> database = new ArrayList<>();

    private ArrayList<String> list = new ArrayList<>();
    private String[] titles = {"A", "C", "D", "E", "F", "G", "H", "I", "K", "L",
            "M", "N", "P", "Q", "R", "S", "T", "V", "W", "Y"};
    private double[] masses = {71.03, 103, .01, 115.03, 129.04, 147.07, 57.02, 137.06, 113.08, 128.09, 113.08,
            131.04, 114.04, 97.05, 128.06, 156.10, 87.03, 101.05, 99.07, 186.08, 163.06};
    private String[][] codons = new String[20][];

    public List<Amino> getDatabase() {
        return database;
    }

    public DataBase() {
        fillCodons();
        for (int i = 0; i < 20; i++) {
            list.clear();
            database.add(new Amino(titles[i], codons[i], masses[i]));

        }
    }

    private ArrayList<String> makeList(String arr[]) {
        ArrayList al = new ArrayList();
        for (String e : arr
                ) {
            al.add(e);
        }
        return al;
    }

    private void fillCodons() {
        codons[0] = new String[]{"GCA", "GCC", "GCG", "GCT"};
        codons[1] = new String[]{"TGC", "TGT"};
        codons[2] = new String[]{"GAC", "GAT"};
        codons[3] = new String[]{"GAA", "GAG"};
        codons[4] = new String[]{"TTC", "TTT"};
        codons[5] = new String[]{"GGA", "GGC", "GGG", "GGT"};
        codons[6] = new String[]{"CAC", "CAT"};
        codons[7] = new String[]{"ATA", "ATC", "ATT"};
        codons[8] = new String[]{"AAA", "AAG"};
        codons[9] = new String[]{"TTA", "TTG", "CTA", "CTC", "CTG", "CTT"};
        codons[10] = new String[]{"ATG"};
        codons[11] = new String[]{"AAC", "AAT"};
        codons[12] = new String[]{"CCA", "CCC", "CCG", "CCT"};
        codons[13] = new String[]{"CAA", "CAG"};
        codons[14] = new String[]{"AGA", "AGG", "CGA", "CGC", "CGG", "CGT"};
        codons[15] = new String[]{"AGC", "AGT", "TCA", "TCC", "TCG", "TCT"};
        codons[16] = new String[]{"ACA", "ACC", "ACG", "ACT"};
        codons[17] = new String[]{"GTA", "GTC", "GTG", "GTT"};
        codons[18] = new String[]{"TGG"};
        codons[19] = new String[]{"TAC", "TAT"};


    }
}
