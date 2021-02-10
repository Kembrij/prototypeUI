package externalcode.technology;

import externalcode.action.Helper;
import externalcode.data.to.operate.Crop;

import java.util.GregorianCalendar;
import java.util.Objects;

public class GrowingTechEntry {
    private final Crop crop;
    private final String activity;
    private final GregorianCalendar startDate;
    private final GregorianCalendar endDate;
    private final int duration;
    private double squareToProcess;    // init in currentQueueGrowingPlan only
    private double daysLeft;           // init in currentQueueGrowingPlan only
    private double queueDaysLeftRatio; // init in currentQueueGrowingPlan only
    private double priority;           // init in currentQueueGrowingPlan only

    public GrowingTechEntry(Crop crop, String activity, GregorianCalendar startDate, GregorianCalendar endDate) {
        this.crop = crop;
        this.activity = activity;
        this.startDate = startDate;
        this.endDate = endDate;
        duration = Helper.getDifferenceDays(startDate, endDate) + 1; // including both start and end dates
    }

    public String getActivity() {
        return activity;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public Crop getCrop() {
        return crop;
    }

    public double getSquareToProcess() {
        return squareToProcess;
    }

    public void setSquareToProcess(double squareToProcess) {
        this.squareToProcess = squareToProcess;
    }

    public int getDuration() {
        return duration;
    }

    public double getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(double daysLeft) {
        this.daysLeft = daysLeft;
    }

    public double getQueueDaysLeftRatio() {
        return queueDaysLeftRatio;
    }

    public void setQueueDaysLeftRatio(double queueDaysLeftRatio) {
        this.queueDaysLeftRatio = queueDaysLeftRatio;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTechEntry entry = (GrowingTechEntry) o;
        return duration == entry.duration
                && crop == entry.crop
                && Objects.equals(activity, entry.activity)
                && Objects.equals(startDate, entry.startDate)
                && Objects.equals(endDate, entry.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crop, activity, startDate, endDate, duration);
    }
}
