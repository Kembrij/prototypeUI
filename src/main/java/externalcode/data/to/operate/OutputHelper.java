package externalcode.data.to.operate;

public class OutputHelper {
    private static final String DAILY_LOG = "out/log.txt";
    private static final String ADVICE = "out/advise.txt";

    public static String getDailyLog() {
        return DAILY_LOG;
    }

    public static String getAdvice() {
        return ADVICE;
    }
}
