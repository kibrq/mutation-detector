package Model;

public class Modification {
    private String name;
    private Amino[] targets;
    private double massDifference;

    public Modification(String name, Amino[] targets, double massDifference) {
        this.name = name;
        this.targets = targets;
        this.massDifference = massDifference;
    }
    public boolean contains(Amino a ){
        for(Amino b : targets){
            if(b.title == a.title){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Amino[] getTargets() {
        return targets;
    }

    public double getMassDifference() {
        return massDifference;
    }

    @Override
    public String toString() {
        return name+" " + targets[0].toString();
    }
}
