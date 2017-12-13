package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private Map<String, ArrayList<String>> database = new HashMap<>();

    public Map<String, ArrayList<String>> getDatabase() {
        return database;
    }

    private ArrayList<String> list = new ArrayList<>();
    private String[] titles = {"Ala", "Cys", "Asp", "Glu", "Phe", "Gly", "His", "Ile", "Lys", "Leu",
            "Met", "Asn", "Pro", "Gln", "Arg", "Ser", "Thr", "Val", "Trp", "Tyr"};
    private String[][] codons = new String[20][];

    public DataBase() {
        fillCodons();
        for (int i = 0; i < 20; i++) {
            list.clear();
            database.put(titles[i], makeList(codons[i]));
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
        codons[0] = new String[]{"GCA", "GCC", "GCG", "GCU"};
        codons[1] = new String[]{"UGC", "UGU"};
        codons[2] = new String[]{"GAC", "GAU"};
        codons[3] = new String[]{"GAA", "GAG"};
        codons[4] = new String[]{"UUC", "UUU"};
        codons[5] = new String[]{"GGA", "GGC", "GGG", "GGU"};
        codons[6] = new String[]{"CAC", "CAU"};
        codons[7] = new String[]{"AUA", "AUC", "AUU"};
        codons[8] = new String[]{"AAA", "AAG"};
        codons[9] = new String[]{"UUA", "UUG", "CUA", "CUC", "CUG", "CUU"};
        codons[10] = new String[]{"AUG"};
        codons[11] = new String[]{"AAC", "AAU"};
        codons[12] = new String[]{"CCA", "CCC", "CCG", "CCU"};
        codons[13] = new String[]{"CAA", "CAG"};
        codons[14] = new String[]{"AGA", "AGG", "CGA", "CGC", "CGG", "CGU"};
        codons[15] = new String[]{"AGC", "AGU", "UCA", "UCC", "UCG", "UCU"};
        codons[16] = new String[]{"ACA", "ACC", "ACG", "ACU"};
        codons[17] = new String[]{"GUA", "GUC", "GUG", "GUU"};
        codons[18] = new String[]{"UGG"};
        codons[19] = new String[]{"UAC", "UAU"};


    }
}
