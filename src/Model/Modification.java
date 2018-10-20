package Model;

public class Modification {
    private String name;
    private double massDifference;

    public Modification(String name, double massDifference) {
        this.name = name;
        this.massDifference = massDifference;
    }


    public String getName() {
        return name;
    }


    public double getMassDifference() {
        return massDifference;
    }

    @Override
    public String toString() {
        return name+" " + massDifference;
    }
}
