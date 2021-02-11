package gui;
import java.util.HashSet;

class Tractor implements Selfmoveable {

    private String name;
    private String type;
    private HashSet<TrailedMachine> listOfCompatibleTrailedMachines;



    private double rateofoutput = 0;


    public Tractor() {
        listOfCompatibleTrailedMachines = new HashSet<>();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setRateofoutput(double rateofoutput) {
        this.rateofoutput = rateofoutput;
    }


    @Override
    public void setRateOfOutPut(double rateofoutput) {
        this.rateofoutput = rateofoutput;
    }

    public void addTolistOfCompatibleTrailedMachine(TrailedMachine tr) {
        listOfCompatibleTrailedMachines.add(tr);
    }
    public boolean isCanBeTrailed(TrailedMachine tm) {
        return listOfCompatibleTrailedMachines.contains(tm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tractor tractor = (Tractor) o;

        if (!name.equals(tractor.name)) return false;
        return listOfCompatibleTrailedMachines.equals(tractor.listOfCompatibleTrailedMachines);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + listOfCompatibleTrailedMachines.hashCode();
        return result;
    }
}
