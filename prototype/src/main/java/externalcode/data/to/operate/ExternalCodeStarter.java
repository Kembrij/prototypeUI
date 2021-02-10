package externalcode.data.to.operate;

import externalcode.action.DynamicDataHolder;
import externalcode.technology.GrowingPlan;
import externalcode.technology.OutputEntry;

import gui.ToolBar;
import gui.ToolBar.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExternalCodeStarter {
    public ExternalCodeStarter() {
        List<String> list = InputStorage.getStringFields();
        System.out.println("Список строк из файла \"Структура посевных площадей.csv:\"");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();

        //System.out.println("Список полей из списка List<Field>:");
        ToolBar.textListener.textEmmited("Список полей из списка List<Field>:" + '\n');
        List<Field> fieldsTest = Property.getAllFields();
        for (Field field : fieldsTest) {
            //System.out.println(field.getName() + " - " + field.getSquare() + " - " + field.getCrop());
            ToolBar.textListener.textEmmited(field.getName() + " - " + field.getSquare() + " - " + field.getCrop() + '\n');
        }
        //System.out.println();
        ToolBar.textListener.textEmmited("" + '\n');

        System.out.println("totalSquareOfAllFields: " + Property.getTotalSquareOfAllFields() + "\n");
        System.out.println("totalSquareOfPeasFields: " + Property.getTotalSquareOfPeasFields() + "\n");
        System.out.println("totalSquareOfSugarBeetFields: " + Property.getTotalSquareOfSugarBeetFields() + "\n");
        System.out.println("totalSquareOfSpringWheatFields: " + Property.getTotalSquareOfSpringWheatFields() + "\n");
        System.out.println("totalSquareOfWinterWheatFields: " + Property.getTotalSquareOfWinterWheatFields() + "\n");


        ToolBar.textListener.textEmmited("totalSquareOfAllFields: " + Property.getTotalSquareOfAllFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfPeasFields: " + Property.getTotalSquareOfPeasFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfSugarBeetFields: " + Property.getTotalSquareOfSugarBeetFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfSpringWheatFields: " + Property.getTotalSquareOfSpringWheatFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfWinterWheatFields: " + Property.getTotalSquareOfWinterWheatFields() + "\n");

        System.out.println("Test print for machinery:");
        System.out.println(Property.getAllMachinery());
        System.out.println(Property.getAllMachinery().size());
        System.out.println();


        ToolBar.textListener.textEmmited("Test print for machinery: ");
        ToolBar.textListener.textEmmited("  " + Property.getAllMachinery());
        ToolBar.textListener.textEmmited("  " + Property.getAllMachinery().size());
        ToolBar.textListener.textEmmited("" + '\n');


        System.out.println("Test print for equipment:");
        System.out.println(Property.getAllEquipment());
        System.out.println(Property.getAllEquipment().size());
        System.out.println();

        ToolBar.textListener.textEmmited("Test print for equipment:");
        ToolBar.textListener.textEmmited("  " + Property.getAllEquipment());
        ToolBar.textListener.textEmmited("  " + Property.getAllEquipment().size());
        ToolBar.textListener.textEmmited("" + '\n');


        //System.out.println("Гороховая технология:");
        ToolBar.textListener.textEmmited("Гороховая технология: ");
        //System.out.println(GrowingPlan.getPeasPlan().size());
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getPeasPlan().size()));

        GrowingPlan.printPlan(GrowingPlan.getPeasPlan());
        System.out.println("Сахарная технология:");
        System.out.println(GrowingPlan.getSugarBeetPlan().size());
        GrowingPlan.printPlan(GrowingPlan.getSugarBeetPlan());
        System.out.println("Яровая технология:");
        System.out.println(GrowingPlan.getSpringWheatPlan().size());
        GrowingPlan.printPlan(GrowingPlan.getSpringWheatPlan());
        System.out.println("Озимая технология:");
        System.out.println(GrowingPlan.getWinterWheatPlan().size());
        GrowingPlan.printPlan(GrowingPlan.getWinterWheatPlan());


        System.out.println("Все технологии в одном списке:");
        System.out.println(GrowingPlan.getCompleteAndSortedByStartPlan().size());
        GrowingPlan.printPlan(GrowingPlan.getCompleteAndSortedByStartPlan());
        System.out.println("Количество смен по видам работ:");
        System.out.println(GrowingPlan.getTypeAndShiftDependency() + "\n");
        System.out.println("Нормы выработки:");
        System.out.println(GrowingPlan.getOutputStandards() + "\n");
        List<OutputEntry> outputStandardsTest = GrowingPlan.getOutputStandards();

        for (OutputEntry outputEntry : outputStandardsTest) {
            System.out.println(
                    outputEntry.getMachineryName() + " - "
                            + outputEntry.getEquipmentName() + " - "
                            + outputEntry.getActivity() + " - "
                            + outputEntry.getOutput()
            );
        }
        System.out.println();


        DynamicDataHolder dynamicDataHolder = new DynamicDataHolder();
        System.out.println("Глобальная очередь работ после создания:");
        GrowingPlan.printPlan(dynamicDataHolder.getGlobalQueueGrowingPlan());
        System.out.println("Текущая очередь работ после создания:");
        GrowingPlan.printPlan(dynamicDataHolder.getCurrentGrowingPlan());
        System.out.println("Продолжительность всех работ составляет, дней: " + dynamicDataHolder.getJobsDuration());
        System.out.println("Дата начала работ по графику: " + dynamicDataHolder.getStartOperations().getTime());
        System.out.println("Дата окончания работ по графику: " + dynamicDataHolder.getStopOperations().getTime());
        System.out.println();


        dynamicDataHolder.doRoutine();
        System.out.println("Глобальная очередь работ после РУТИНЫ:");
        GrowingPlan.printPlan(dynamicDataHolder.getGlobalQueueGrowingPlan());
        System.out.println("Текущая очередь работ после РУТИНЫ:");
        GrowingPlan.printPlan(dynamicDataHolder.getCurrentGrowingPlan());

        System.out.println("Виды активностей: ");
        System.out.println(GrowingPlan.getActivityTypesSet());
        System.out.println("Количество разных активностей: " + GrowingPlan.getActivityTypesSet().size());

        System.out.println("\nСтандарты обработки по видам активностей:\n");
        Map<String, ArrayList<OutputEntry>> testMap = GrowingPlan.getOutputStandardsGroupedByActivity();


        for (Map.Entry<String, ArrayList<OutputEntry>> entry : testMap.entrySet()) {
            System.out.println(entry.getKey());
            ArrayList<OutputEntry> entryArrayList = entry.getValue();
            for (OutputEntry outputEntry : entryArrayList) {
                System.out.println(
                        outputEntry.getMachineryName() + " - " +
                                outputEntry.getEquipmentName() + " - " +
                                outputEntry.getActivity() + " - " +
                                outputEntry.getOutput()
                );
            }
            System.out.println();
        }


        System.out.println("Незавершенные работы:");
        System.out.println(dynamicDataHolder.getFailedOperations().size());
        GrowingPlan.printPlan(dynamicDataHolder.getFailedOperations());

    }
}
