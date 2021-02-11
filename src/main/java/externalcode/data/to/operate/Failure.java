package externalcode.data.to.operate;

import java.util.GregorianCalendar;

public class Failure {
    private final double squareToProcessRemains;
    private final int duration;
    private final double squarePerDayRemain;
    private final Crop crop;
    private final String activity;
    private final GregorianCalendar startDate;
    private final GregorianCalendar endDate;

    public Failure(double squareToProcessRemains, int duration,
                   double squarePerDayRemain, Crop crop, String activity,
                   GregorianCalendar startDate, GregorianCalendar endDate) {
        this.squareToProcessRemains = squareToProcessRemains;
        this.duration = duration;
        this.squarePerDayRemain = squarePerDayRemain;
        this.crop = crop;
        this.activity = activity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getSquareToProcessRemains() {
        return squareToProcessRemains;
    }

    public int getDuration() {
        return duration;
    }

    public double getSquarePerDayRemain() {
        return squarePerDayRemain;
    }

    public Crop getCrop() {
        return crop;
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
}
