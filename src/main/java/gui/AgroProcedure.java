package gui;
import java.time.LocalDate;
import java.util.Calendar;


public class AgroProcedure {

    private String name;
    private Calendar startOfProcedure;
    private Calendar endOfProcedure;
    private double numberOfShifts;


    public AgroProcedure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Calendar getStartOfProcedure() {
        return startOfProcedure;
    }

    public Calendar getEndOfProcedure() {
        return endOfProcedure;
    }

    public double getNumberOfshifts() {
        return numberOfShifts;
    }

    public void setNumberOfshifts(double numberOfshifts) {
        this.numberOfShifts = numberOfshifts;
    }

    public void setStartOfProcedure(Calendar startOfProcedure) {
        this.startOfProcedure = startOfProcedure;
    }

    public void setEndOfProcedure(Calendar endOfProcedure) {
        this.endOfProcedure = endOfProcedure;
    }

    public void setNumberOfShifts(double numberOfShifts) {
        this.numberOfShifts = numberOfShifts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgroProcedure procedure = (AgroProcedure) o;

        if (Double.compare(procedure.numberOfShifts, numberOfShifts) != 0) return false;
        if (!name.equals(procedure.name)) return false;
        if (!startOfProcedure.equals(procedure.startOfProcedure)) return false;
        return endOfProcedure.equals(procedure.endOfProcedure);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + startOfProcedure.hashCode();
        result = 31 * result + endOfProcedure.hashCode();
        temp = Double.doubleToLongBits(numberOfShifts);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

