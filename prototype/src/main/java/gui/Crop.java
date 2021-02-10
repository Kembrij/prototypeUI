package gui;
import java.util.ArrayList;
import java.util.LinkedList;

class Crop implements Harvestable {

    private String nameOfCrop;
    private ArrayList<AgroProcedure> listOfProcedure;
    int index;

    public Crop(String nameOfCrop) {
        this.nameOfCrop = nameOfCrop;
        listOfProcedure = new ArrayList<>();
        index = 0;

    }

    public ArrayList<AgroProcedure> getListOfProcedures () {
        return listOfProcedure;
    }

    public int sizeOfListOfProcedure() {
        return listOfProcedure.size();
    }

    public String getNameOfCrop() {
        return nameOfCrop;
    }

    public void addAgroProcedure(AgroProcedure a) {
        listOfProcedure.add(a);
    }

    public AgroProcedure pollProcedure() {
        if (index == listOfProcedure.size()) {
            index = 0;
        }
        return listOfProcedure.get(index++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crop crop = (Crop) o;

        if (!nameOfCrop.equals(crop.nameOfCrop)) return false;
        return listOfProcedure.equals(crop.listOfProcedure);
    }

    @Override
    public int hashCode() {
        int result = nameOfCrop.hashCode();
        result = 31 * result + listOfProcedure.hashCode();
        return result;
    }
}
