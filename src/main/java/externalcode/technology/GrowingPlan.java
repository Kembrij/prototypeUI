package externalcode.technology;

import externalcode.data.to.operate.Crop;
import externalcode.data.to.operate.InputStorage;
import gui.ToolBar;

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
        double[] array = new double[3];
        for (int i = 0; i < 3; i++) {
            array[i] = Double.parseDouble(strings[i]);
        }
        return array;
    }

    private static GregorianCalendar getNewGregorianCalendar(double[] array) {
        return new GregorianCalendar(
                (int) array[2],
                (int) array[1] - 1,
                (int) array[0]
        );
    }

    public static void printPlan(List<GrowingTechEntry> list) {

        for (GrowingTechEntry gt: list) {
            StringBuilder sb = new StringBuilder();
            sb.append(gt.getCrop().toString());

            for (int i = 0; i < 25 - gt.getCrop().toString().length(); i++) {
                sb.append(' ');
            }
            sb.append(gt.getActivity());
            for (int i = 0; i < 80 - gt.getActivity().length(); i++) {
                sb.append(' ');
            }
            sb.append(gt.getStartDate().get(Calendar.DAY_OF_MONTH) +"."+gt.getStartDate().get(Calendar.MONTH) +"."+gt.getStartDate().get(Calendar.YEAR));
            for (int i = 0; i < 10; i++) {
                sb.append(' ');
            }
            sb.append(gt.getEndDate().get(Calendar.DAY_OF_MONTH) +"."+gt.getEndDate().get(Calendar.MONTH) +"."+gt.getEndDate().get(Calendar.YEAR));
            for (int i = 0; i < 10; i++) {
                sb.append(' ');
            }
            sb.append(gt.getDuration());
            for (int i = 0; i < 5; i++) {
                sb.append(' ');
            }
            sb.append("дней");
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append("необходимо обработать: ");
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append(gt.getSquareToProcess());
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append("срок окончания: ");
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append(gt.getDaysLeft());
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append("нагрузка: ");
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append(gt.getQueueDaysLeftRatio());
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append("приоритет: ");
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            sb.append(gt.getPriority());
            for (int i = 0; i < 4; i++) {
                sb.append(' ');
            }
            ToolBar.textListener.textEmmited(sb.toString());
            ToolBar.textListener.textEmmited("" +'\n');
            sb = null;

        }


       /* for (GrowingTechEntry gt : list) {
            StringBuilder sb = new StringBuilder();
            Formatter fm = new Formatter(sb);
            fm.format("%1$-20s%2$-100s%3$-3d.%4$-3d.%5$-5d%6$-3d.%7$-3d.%8$-5d%9$-4d%10$-20s%11$-20s%" +
                            "12$-5.2f%13$-15s%14$-4.2f%15$-15s%16$-5.2f%17$-15s%18$-4f",
                    gt.getCrop().toString(),
                    gt.getActivity(),
                    gt.getStartDate().get(Calendar.DAY_OF_MONTH),
                    gt.getStartDate().get(Calendar.MONTH),
                    gt.getStartDate().get(Calendar.YEAR),

                    gt.getEndDate().get(Calendar.DAY_OF_MONTH),
                    gt.getEndDate().get(Calendar.MONTH),
                    gt.getEndDate().get(Calendar.YEAR),
                    gt.getDuration(),
                    "дней",
                    "необходимо обработать: ",
                    gt.getSquareToProcess(),
                    "срок окончания: ",
                    gt.getDaysLeft(),
                    "нагрузка: ",
                    gt.getQueueDaysLeftRatio(),
                    "приоритет: ",
                    gt.getPriority());

            //sb = new StringBuilder();
            //fm = new Formatter(sb);
            ToolBar.textListener.textEmmited(fm.toString());
            System.out.println(fm.toString());
            ToolBar.textListener.textEmmited("" + '\n');
        }*/


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
