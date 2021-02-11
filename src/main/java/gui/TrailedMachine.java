package gui;
import java.util.HashSet;

class TrailedMachine {

    private String name;
    private String type;
    private double rateofoutput;
    double rateOfProductionPerShift;

    private HashSet<Tractor> listOfCompatibleTractors;
    private AgroProcedure agroProcedure;



    public TrailedMachine() {
        listOfCompatibleTractors = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addTolistOfCompatibleTractors(Tractor tractor) {
        listOfCompatibleTractors.add(tractor);
    }

    public AgroProcedure getAgroProcedure() {
        return agroProcedure;
    }

    public double getRateOfProductionPerShift() {
        return rateOfProductionPerShift;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRateofoutput() {
        return rateofoutput;
    }

    public void setRateOfOutPut(double rateofoutput) {
        this.rateofoutput = rateofoutput;
    }

    public String getType() {
        return type;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrailedMachine that = (TrailedMachine) o;

        if (Double.compare(that.rateOfProductionPerShift, rateOfProductionPerShift) != 0) return false;
        if (!name.equals(that.name)) return false;
        if (!listOfCompatibleTractors.equals(that.listOfCompatibleTractors)) return false;
        return agroProcedure.equals(that.agroProcedure);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + listOfCompatibleTractors.hashCode();
        result = 31 * result + agroProcedure.hashCode();
        temp = Double.doubleToLongBits(rateOfProductionPerShift);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
