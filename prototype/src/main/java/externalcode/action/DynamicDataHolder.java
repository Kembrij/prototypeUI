package externalcode.action;

import externalcode.data.to.operate.Crop;
import externalcode.data.to.operate.Property;
import externalcode.technology.GrowingPlan;
import externalcode.technology.GrowingTechEntry;
import externalcode.technology.OutputEntry;

import java.util.*;

public class DynamicDataHolder {
    private final LinkedList<GrowingTechEntry> globalQueueGrowingPlan = new LinkedList<>(GrowingPlan.getCompleteAndSortedByStartPlan());

    private final Calendar startOperations;
    private final Calendar stopOperations;

    {
        assert globalQueueGrowingPlan.peekFirst() != null;
        startOperations = globalQueueGrowingPlan.peekFirst().getStartDate();
    }

    {
        assert globalQueueGrowingPlan.peekLast() != null;
        stopOperations = globalQueueGrowingPlan.peekLast().getStartDate();
    }

    Calendar currentDay = initCurrentDay();

    private final int jobsDuration = Helper.getDifferenceDays(startOperations, stopOperations);

    private final List<GrowingTechEntry> currentGrowingPlan = new ArrayList<>();
    private final List<GrowingTechEntry> failedOperations = new ArrayList<>();

    private Map<String, String> machineryToChoose;
    private Map<String, String> equipmentToChoose;

    public Calendar getCurrentDay() {
        return currentDay;
    }

    private Calendar initCurrentDay() {
        return (Calendar) startOperations.clone();
    }

    boolean flag = true;

    public void doRoutine() {
        while (flag) {
            makeDailyCheckOfCurrentPlan();
            takeMachineryAndEquipmentAndWork();
            removeOneDay();
            checkForCompletedTasks();
        }
    }

    private void makeDailyCheckOfCurrentPlan() {
        while (flag) {
            if (globalQueueGrowingPlan.isEmpty()) {
                if (!currentGrowingPlan.isEmpty()) {
                    System.out.println(currentGrowingPlan.size());
                    for (GrowingTechEntry toComplete : currentGrowingPlan) {
                        checkForCompletedTasks();
                        System.out.println(toComplete.getSquareToProcess());
                        System.out.println("Начало новых работ не запланировано! Завершаем уже начатые!");
                        addOneDayToCurrentDay();
                        break;
                    }
                }
                break;
            } else {
                GrowingTechEntry techEntry = globalQueueGrowingPlan.peekFirst();
                if (currentDay.equals(techEntry.getStartDate())) {
                    techEntry.setSquareToProcess(initSquareToProcess(techEntry.getCrop()));
                    techEntry.setDaysLeft(initDaysToEnd(techEntry));
                    techEntry.setQueueDaysLeftRatio(techEntry.getSquareToProcess() / techEntry.getDaysLeft());
                    techEntry.setPriority(techEntry.getDaysLeft() * techEntry.getQueueDaysLeftRatio());
                    currentGrowingPlan.add(removeAndGetFirstFormGlobalQueueGrowingPlan());
                    currentGrowingPlan.sort(Comparator.comparingDouble(GrowingTechEntry::getPriority).reversed());
                    if (globalQueueGrowingPlan.size() == 0) {
                        break;
                    }
                } else {
                    addOneDayToCurrentDay();
                    break;
                }
            }
        }
    }

    private void takeMachineryAndEquipmentAndWork() {
        machineryToChoose = Property.getAllMachinery();
        equipmentToChoose = Property.getAllEquipment();
        for (GrowingTechEntry growingTechEntry : currentGrowingPlan) {
            System.out.println(
                    currentDay.getTime() + " : " +
                    "Площадь под обработку в очереди: " + growingTechEntry.getSquareToProcess() +
                    ".Культура: " + growingTechEntry.getCrop() +
                    ".Вид: " + growingTechEntry.getActivity() +
                    ".Дедлайн: " + growingTechEntry.getEndDate().getTime() +
                    ".Осталось: " + growingTechEntry.getDaysLeft()
            );
            ArrayList<OutputEntry> sortedByActivityOutputStandards =
                    GrowingPlan.getListFromOutputStandardsGroupedByActivity(growingTechEntry.getActivity());
            for (OutputEntry outputEntry : sortedByActivityOutputStandards) {
                if (machineryToChoose.containsValue(outputEntry.getMachineryName()) &&
                        equipmentToChoose.containsValue(outputEntry.getEquipmentName())) {
                    growingTechEntry.setSquareToProcess(
                            growingTechEntry.getSquareToProcess() -
                            (int) (GrowingPlan.getTypeAndShiftDependency().get(growingTechEntry.getActivity()) *
                            outputEntry.getOutput()));
                    System.out.println(
                            currentDay.getTime() + " : " +
                            "Площадь под обработку: " + growingTechEntry.getSquareToProcess() +
                            ".Культура: " + growingTechEntry.getCrop() +
                            ".Вид: " + growingTechEntry.getActivity() +
                            ".Дедлайн: " + growingTechEntry.getEndDate().getTime() +
                            ".Осталось: " + growingTechEntry.getDaysLeft() + "\n");
                    if (growingTechEntry.getSquareToProcess() <= 0) {
                        System.out.println("Завершение обработки культуры по данному виду работы!");
                        break;
                    }
                }
                machineryToChoose.remove(outputEntry.getMachineryName());
                equipmentToChoose.remove(outputEntry.getEquipmentName());
            }
        }
    }

    private void checkForCompletedTasks() {
        if (currentGrowingPlan.isEmpty() && globalQueueGrowingPlan.isEmpty()) {
            flag = false;
        }
        currentGrowingPlan.removeIf(entry -> entry.getSquareToProcess() <= 0);
        LinkedList<GrowingTechEntry> temp = new LinkedList<>(currentGrowingPlan);
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getDaysLeft() <= 0) {
                System.out.println(
                        "Не хватило техники для обработки полей с культурой: " + temp.get(i).getCrop() +
                        ". Отсалось обработать: " + temp.get(i).getSquareToProcess() +
                        ". Неоконченный вид работы: " + temp.get(i).getActivity()
                );
                failedOperations.add(currentGrowingPlan.remove(i));
                break;
            }
        }
    }

    private void removeOneDay() {
        for (GrowingTechEntry entry : currentGrowingPlan) {
            entry.setDaysLeft(entry.getDaysLeft() - 1);
        }
    }

    private double initSquareToProcess(Crop crop) {
        switch (crop) {
            case PEAS:
                return Property.getTotalSquareOfPeasFields();
            case SUGAR_BEET:
                return Property.getTotalSquareOfSugarBeetFields();
            case SPRING_WHEAT:
                return Property.getTotalSquareOfSpringWheatFields();
            case WINTER_WHEAT:
                return Property.getTotalSquareOfWinterWheatFields();
            default:
                return 0;
        }
    }

    private int initDaysToEnd(GrowingTechEntry growingTechEntry) {
        return growingTechEntry.getDuration();
    }

    public void addOneDayToCurrentDay() {
        currentDay.add(Calendar.DAY_OF_YEAR, 1);
    }

    public List<GrowingTechEntry> getGlobalQueueGrowingPlan() {
        return globalQueueGrowingPlan;
    }

    public Calendar getStartOperations() {
        return startOperations;
    }

    public Calendar getStopOperations() {
        return stopOperations;
    }

    public long getJobsDuration() {
        return jobsDuration;
    }

    public GrowingTechEntry removeAndGetFirstFormGlobalQueueGrowingPlan() {
        return globalQueueGrowingPlan.pollFirst();
    }

    public List<GrowingTechEntry> getCurrentGrowingPlan() {
        return currentGrowingPlan;
    }

    public List<GrowingTechEntry> getFailedOperations() {
        return failedOperations;
    }
}
