package gui;
import java.util.HashSet;

class Sprayer implements Selfmoveable {

    private String name;
    private String type;



    private double rateofoutput;



    public Sprayer() {
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setRateOfOutPut(double rateofoutput) {

    }

    public double getRateofoutput() {
        return rateofoutput;
    }

    public void setRateofoutput(double rateofoutput) {
        this.rateofoutput = rateofoutput;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprayer sprayer = (Sprayer) o;

        if (!name.equals(sprayer.name)) return false;
        return type.equals(sprayer.type);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
