package externalcode.data.to.operate;

public class Field {
    private final String name;
    private final double square;
    private Crop crop;

    public Field(String name, double square, Crop crop) {
        this.name = name;
        this.square = square;
        this.crop = crop;
    }

    public String getName() {
        return name;
    }

    public double getSquare() {
        return square;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
