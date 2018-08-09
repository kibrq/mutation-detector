package Database;

import Model.Amino;

import java.util.*;

public class DataBase {
    final private ArrayList<Amino> database = new ArrayList<>();

    private ArrayList<String> list = new ArrayList<>();
    final public String[] titles = {"A", "C", "D", "E", "F", "G", "H", "I", "K", "L",
            "M", "N", "P", "Q", "R", "S", "T", "V", "W", "Y"};
    final private double[] masses = {71.03711, 103.00919, 115.02694, 129.04259, 147.06841, 57.02146, 137.05891, 113.08406, 128.09496, 113.08406,
            131.04049, 114.04293, 97.05276, 128.05858, 156.10111, 87.03203, 101.04768, 99.06841, 186.07931, 163.06333};
    private String[][] codons = new String[20][];

    public ArrayList<Amino> getDatabase() {
        return database;
    }

    public DataBase() {
        fillCodons();
        for (int i = 0; i < 20; i++) {
            list.clear();
            database.add(new Amino(titles[i], codons[i], masses[i]));

        }
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
