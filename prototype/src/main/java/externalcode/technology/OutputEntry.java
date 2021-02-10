package externalcode.technology;

import java.util.Objects;

public class OutputEntry {
    private final String machineryName;
    private final String equipmentName;
    private final String activity;
    private final double output;

    public OutputEntry(String machineryName, String equipmentName, String activity, double output) {
        this.machineryName = machineryName;
        this.equipmentName = equipmentName;
        this.activity = activity;
        this.output = output;
    }

    public String getMachineryName() {
        return machineryName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getActivity() {
        return activity;
    }

    public double getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputEntry that = (OutputEntry) o;
        return Double.compare(that.output, output) == 0 &&
                machineryName.equals(that.machineryName) &&
                equipmentName.equals(that.equipmentName) &&
                activity.equals(that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineryName, equipmentName, activity, output);
    }
}
