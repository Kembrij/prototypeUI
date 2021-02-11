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
/*
        List<String> list = InputStorage.getStringFields();
        for (String s : list) {
            ToolBar.textListener.textEmmited(s+ '\n');
        }


        ToolBar.textListener.textEmmited("Список полей из списка List<Field>:" + '\n');
        List<Field> fieldsTest = Property.getAllFields();
        for (Field field : fieldsTest) {

            ToolBar.textListener.textEmmited(field.getName() + " - " + field.getSquare() + " - " + field.getCrop() + '\n');
        }

        ToolBar.textListener.textEmmited("" + '\n');

*/

/*

        ToolBar.textListener.textEmmited("totalSquareOfAllFields: " + Property.getTotalSquareOfAllFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfPeasFields: " + Property.getTotalSquareOfPeasFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfSugarBeetFields: " + Property.getTotalSquareOfSugarBeetFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfSpringWheatFields: " + Property.getTotalSquareOfSpringWheatFields() + "\n");
        ToolBar.textListener.textEmmited("totalSquareOfWinterWheatFields: " + Property.getTotalSquareOfWinterWheatFields() + "\n");
*/



/*        ToolBar.textListener.textEmmited("Test print for machinery: ");
        ToolBar.textListener.textEmmited("  " + Property.getAllMachinery());
        ToolBar.textListener.textEmmited("  " + Property.getAllMachinery().size());
        ToolBar.textListener.textEmmited("" + '\n');


        ToolBar.textListener.textEmmited("Test print for equipment:");
        ToolBar.textListener.textEmmited("  " + Property.getAllEquipment());
        ToolBar.textListener.textEmmited("  " + Property.getAllEquipment().size());
        ToolBar.textListener.textEmmited("" + '\n');*/




        ToolBar.textListener.textEmmited("Гороховая технология: ");

        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getPeasPlan().size()));

        GrowingPlan.printPlan(GrowingPlan.getPeasPlan());
        ToolBar.textListener.textEmmited("Сахарная технология:");
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getSugarBeetPlan().size()));
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getSugarBeetPlan()));
        ToolBar.textListener.textEmmited("Яровая технология:");
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getSpringWheatPlan().size()));
        GrowingPlan.printPlan(GrowingPlan.getSpringWheatPlan());
        ToolBar.textListener.textEmmited("Озимая технология:");
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getWinterWheatPlan().size()));
        GrowingPlan.printPlan(GrowingPlan.getWinterWheatPlan());


        ToolBar.textListener.textEmmited("Все технологии в одном списке:");
        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getCompleteAndSortedByStartPlan().size()));
        GrowingPlan.printPlan(GrowingPlan.getCompleteAndSortedByStartPlan());
        ToolBar.textListener.textEmmited("Количество смен по видам работ:");
        ToolBar.textListener.textEmmited(GrowingPlan.getTypeAndShiftDependency() + "\n");
        ToolBar.textListener.textEmmited("Нормы выработки:");
        ToolBar.textListener.textEmmited(GrowingPlan.getOutputStandards() + "\n");
        List<OutputEntry> outputStandardsTest = GrowingPlan.getOutputStandards();

        for (OutputEntry outputEntry : outputStandardsTest) {
            ToolBar.textListener.textEmmited(
                    outputEntry.getMachineryName() + " - "
                            + outputEntry.getEquipmentName() + " - "
                            + outputEntry.getActivity() + " - "
                            + outputEntry.getOutput()
            );
        }
        ToolBar.textListener.textEmmited(""+'\n');


        DynamicDataHolder dynamicDataHolder = new DynamicDataHolder();
        ToolBar.textListener.textEmmited("Глобальная очередь работ после создания:");
        GrowingPlan.printPlan(dynamicDataHolder.getGlobalQueueGrowingPlan());
        ToolBar.textListener.textEmmited("Текущая очередь работ после создания:");
        GrowingPlan.printPlan(dynamicDataHolder.getCurrentGrowingPlan());
        ToolBar.textListener.textEmmited("Продолжительность всех работ составляет, дней: " + dynamicDataHolder.getJobsDuration());
        ToolBar.textListener.textEmmited("Дата начала работ по графику: " + dynamicDataHolder.getStartOperations().getTime());
        ToolBar.textListener.textEmmited("Дата окончания работ по графику: " + dynamicDataHolder.getStopOperations().getTime());
        ToolBar.textListener.textEmmited(""+'\n');




        dynamicDataHolder.doRoutine();

        ToolBar.textListener.textEmmited("Глобальная очередь работ после РУТИНЫ: "+'\n');
        GrowingPlan.printPlan(dynamicDataHolder.getGlobalQueueGrowingPlan());
        ToolBar.textListener.textEmmited("Текущая очередь работ после РУТИНЫ: "+'\n');
        GrowingPlan.printPlan(dynamicDataHolder.getCurrentGrowingPlan());


        ToolBar.textListener.textEmmited("Виды активностей: ");

        ToolBar.textListener.textEmmited(String.valueOf(GrowingPlan.getActivityTypesSet()));
        ToolBar.textListener.textEmmited(" Количество разных активностей: " + GrowingPlan.getActivityTypesSet().size()+'\n');

        //System.out.println("\nСтандарты обработки по видам активностей:\n");
        Map<String, ArrayList<OutputEntry>> testMap = GrowingPlan.getOutputStandardsGroupedByActivity();


        for (Map.Entry<String, ArrayList<OutputEntry>> entry : testMap.entrySet()) {
            ToolBar.textListener.textEmmited(entry.getKey()+ " ");
            ArrayList<OutputEntry> entryArrayList = entry.getValue();
            for (OutputEntry outputEntry : entryArrayList) {
                ToolBar.textListener.textEmmited(outputEntry.getMachineryName() + " - " + outputEntry.getEquipmentName() + " - " +
                                outputEntry.getActivity() + " - " + outputEntry.getOutput()+'\n');
            }
            ToolBar.textListener.textEmmited(""+'\n');
        }

        ToolBar.textListener.textEmmited("Незавершенные работы: ");
        ToolBar.textListener.textEmmited(String.valueOf(dynamicDataHolder.getFailedOperations().size())+'\n');
        GrowingPlan.printPlan(dynamicDataHolder.getFailedOperations());




    }
}
