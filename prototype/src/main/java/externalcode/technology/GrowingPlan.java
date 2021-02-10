package externalcode.technology;

import externalcode.data.to.operate.Crop;
import externalcode.data.to.operate.InputStorage;

import java.util.*;

public class GrowingPlan {
    private static final List<GrowingTechEntry> PEAS_PLAN = initPeasPlan();
    private static final List<GrowingTechEntry> SUGAR_BEET_PLAN = initSugarBeetPlan();
    private static final List<GrowingTechEntry> SPRING_WHEAT_PLAN = initSpringWheatPlan();
    private static final List<GrowingTechEntry> WINTER_WHEAT_PLAN = initWinterWheatPlan();
    private static final List<GrowingTechEntry> COMPLETE_AND_SORTED_BY_START_PLAN
            = initCompleteAndSortedByStartPlan();

    private static final Map<String, Double> TYPE_AND_SHIFT_DEPENDENCY = initTypeAndShiftDependency();
    private static final List<OutputEntry> OUTPUT_STANDARDS = initOutputStandards();
    private static final Set<String> ACTIVITY_TYPES_SET = initActivityTypesSet();

    private static final Map<String, ArrayList<OutputEntry>> OUTPUT_STANDARDS_GROUPED_BY_ACTIVITY
            = initOutputStandardsGroupedByActivity();

    public static ArrayList<OutputEntry> getListFromOutputStandardsGroupedByActivity(String activity) {
        return OUTPUT_STANDARDS_GROUPED_BY_ACTIVITY.get(activity);
    }

    public static List<GrowingTechEntry> getPeasPlan() {
        return PEAS_PLAN;
    }

    public static List<GrowingTechEntry> getSugarBeetPlan() {
        return SUGAR_BEET_PLAN;
    }

    public static List<GrowingTechEntry> getSpringWheatPlan() {
        return SPRING_WHEAT_PLAN;
    }

    public static List<GrowingTechEntry> getWinterWheatPlan() {
        return WINTER_WHEAT_PLAN;
    }

    public static List<GrowingTechEntry> getCompleteAndSortedByStartPlan() {
        return COMPLETE_AND_SORTED_BY_START_PLAN;
    }

    public static Map<String, Double> getTypeAndShiftDependency() {
        return TYPE_AND_SHIFT_DEPENDENCY;
    }

    public static List<OutputEntry> getOutputStandards() {
        return OUTPUT_STANDARDS;
    }

    public static Set<String> getActivityTypesSet() {
        return ACTIVITY_TYPES_SET;
    }

    public static Map<String, ArrayList<OutputEntry>> getOutputStandardsGroupedByActivity() {
        return OUTPUT_STANDARDS_GROUPED_BY_ACTIVITY;
    }

    private static List<GrowingTechEntry> initPeasPlan() {
        List<String> list = InputStorage.getStringPeasPlan();
        return getGrowingPlan(list, Crop.PEAS);
    }

    private static List<GrowingTechEntry> initSugarBeetPlan() {
        List<String> list = InputStorage.getStringSugarBeetPlan();
        return getGrowingPlan(list, Crop.SUGAR_BEET);
    }

    private static List<GrowingTechEntry> initSpringWheatPlan() {
        List<String> list = InputStorage.getStringSpringWheatPlan();
        return getGrowingPlan(list, Crop.SPRING_WHEAT);
    }

    private static List<GrowingTechEntry> initWinterWheatPlan() {
        List<String> list = InputStorage.getStringWinterWheatPlan();
        return getGrowingPlan(list, Crop.WINTER_WHEAT);
    }

    private static List<GrowingTechEntry> initCompleteAndSortedByStartPlan() {
        List<GrowingTechEntry> list = new ArrayList<>();
        list.addAll(PEAS_PLAN);
        list.addAll(SUGAR_BEET_PLAN);
        list.addAll(SPRING_WHEAT_PLAN);
        list.addAll(WINTER_WHEAT_PLAN);
        list.sort((o1, o2) -> {
            if (o1.getStartDate().before(o2.getStartDate())) {
                return -1;
            }
            if (o1.getStartDate().after(o2.getStartDate())) {
                return 1;
            }
            return 0;
        });
        return list;
    }

    private static List<GrowingTechEntry> getGrowingPlan(List<String> list, Crop crop) {
        List<GrowingTechEntry> growingTechEntryList = new ArrayList<>();
        for (String s : list) {
            String[] splittedS = s.split(";");

            String[] startDateInStringArray = splittedS[1].split("\\.");
            double[] startDateIntArray = getDoubleArray(startDateInStringArray);

            String[] endDateInStringArray = splittedS[2].split("\\.");
            double[] endDateIntArray = getDoubleArray(endDateInStringArray);

            GregorianCalendar start = getNewGregorianCalendar(startDateIntArray);
            GregorianCalendar end = getNewGregorianCalendar(endDateIntArray);

            growingTechEntryList.add(new GrowingTechEntry(crop, splittedS[0].trim(), start, end));

        }
        return growingTechEntryList;
    }

    private static double[] getDoubleArray(String[] strings) {
        double [] array = new  double[3];
        for (int i = 0; i < 3; i++) {
            System.out.println(strings[i]);
            array[i] = Double.parseDouble(strings[i]);
        }
        return array;
    }

    private static GregorianCalendar getNewGregorianCalendar(double[] array) {
        return new GregorianCalendar(
                (int) array[2],
                (int)array[1] - 1,
                (int)array[0]
        );
    }

    public static void printPlan(List<GrowingTechEntry> list) {
        list.forEach(e -> System.out.println(
                e.getCrop() + " - " +
                        e.getActivity() + " - " +
                        e.getStartDate().get(Calendar.DAY_OF_MONTH) + ":" +
                        e.getStartDate().get(Calendar.MONTH) + ":" +
                        e.getStartDate().get(Calendar.YEAR) + " - " +
                        e.getEndDate().get(Calendar.DAY_OF_MONTH) + ":" +
                        e.getEndDate().get(Calendar.MONTH) + ":" +
                        e.getEndDate().get(Calendar.YEAR) + " - " +
                        e.getDuration() + " дней - " +
                        "необходимо обработать: " + e.getSquareToProcess() +
                        " - срок окончания: " + e.getDaysLeft() +
                        " - нагрузка: " + e.getQueueDaysLeftRatio() +
                        " - приоритет: " + e.getPriority()
        ));
        System.out.println();
    }

    private static Map<String, Double> initTypeAndShiftDependency() {
        List<String> list = InputStorage.getStringTypeAndShiftDependency();
        Map<String, Double> map = new HashMap<>();
        for (String s : list) {
            String[] splittedS = s.split(";");
            map.put(splittedS[0].trim(), Double.parseDouble(splittedS[1].trim()));
        }
        return map;
    }

    private static List<OutputEntry> initOutputStandards() {
        List<String> list = InputStorage.getStringOutputStandards();
        List<OutputEntry> outputEntries = new ArrayList<>();
        for (String line : list) {
            String[] splittedLine = line.split(";");
            String tempDoubleString = splittedLine[3].replaceAll(",", "\\.");
            outputEntries.add(new OutputEntry(
                    splittedLine[0].trim(),
                    splittedLine[1].trim(),
                    splittedLine[2].trim(),
                    Double.parseDouble(tempDoubleString)
            ));
        }
        return outputEntries;
    }

    private static Set<String> initActivityTypesSet() {
        List<String> list = InputStorage.getStringOutputStandards();
        Set<String> set = new TreeSet<>();
        for (String line : list) {
            String[] splittedLine = line.split(";");
            set.add(splittedLine[2].trim());
        }
        return set;
    }

    private static Map<String, ArrayList<OutputEntry>> initOutputStandardsGroupedByActivity() {
        Map<String, ArrayList<OutputEntry>> map = new HashMap<>();
        for (String activityType : ACTIVITY_TYPES_SET) {
            ArrayList<OutputEntry> list = new ArrayList<>();
            for (OutputEntry outputEntry : OUTPUT_STANDARDS) {
                if (activityType.equals(outputEntry.getActivity())) {
                    list.add(outputEntry);
                }
            }
            list.sort(Comparator.comparingDouble(OutputEntry::getOutput).reversed());
            map.put(activityType, list);
        }
        return map;
    }
}
