package Model;

public enum AminoAcid {
    A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y;
    private String title;
    private double mass;
    private String[] codons;
    private String fullName;

    public static void setData() {
        final String[] titles = {"A", "C", "D", "E", "F", "G", "H", "I", "K", "L",
                "M", "N", "P", "Q", "R", "S", "T", "V", "W", "Y"};

        String[] codons[] = new String[20][];
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

        double[] masses = {71.03711, 103.00919, 115.02694, 129.04259, 147.06841, 57.02146, 137.05891, 113.08406, 128.09496, 113.08406,
                131.04049, 114.04293, 97.05276, 128.05858, 156.10111, 87.03203, 101.04768, 99.06841, 186.07931, 163.06333};

        String[] fullNames = {"alanine", "cysteine", "aspartic acid", "glutamic acid", "phenylalanine", "glycine", "histidine", "isoleucine",
                "lysine", "leucine", "methionine", "asparagine", "proline", "glutamine", "arginine", "serine", "threonine", "valine", "tryptophan", "tyrosine"};
        AminoAcid[] ad = AminoAcid.values();
        for (int i = 0; i < 20; i++) {
            ad[i].title = titles[i];
            ad[i].mass = masses[i];
            ad[i].codons = codons[i];
            ad[i].fullName = fullNames[i];
        }
    }

    public static AminoAcid valueOf(Character c) {
        return AminoAcid.valueOf(Character.toString(c));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String[] getCodons() {
        return codons;
    }

    public void setCodons(String[] codons) {
        this.codons = codons;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return title + mass;
    }
}


