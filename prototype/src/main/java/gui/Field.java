package gui;
public class Field {

    private String nameId;
    private Double area;
    private String nameOfCrop;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    private int rowNum;

    public Field(String nameId) {
        this.nameId = nameId;

    }

    public String getNameId() {
        return nameId;
    }

    public Double getArea() {
        return area;
    }

    public String getCrop() {
        return nameOfCrop;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setCrop(String crop) {
        this.nameOfCrop = crop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (!nameId.equals(field.nameId)) return false;
        if (!area.equals(field.area)) return false;
        return nameOfCrop.equals(field.nameOfCrop);
    }

    @Override
    public int hashCode() {
        int result = nameId.hashCode();
        result = 31 * result + area.hashCode();
        result = 31 * result + nameOfCrop.hashCode();
        return result;
    }
}
