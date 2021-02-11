package externalcode.data.to.operate;

import externalcode.action.Helper;
import gui.*;
import org.jfree.data.DataUtilities;


import java.util.ArrayList;
import java.util.List;

public class InputStorage {
    private static final String FIELDS_PATH = "inputdata/Структура посевных площадей.csv";
    private static final String MACHINERY = "inputdata/Самоходная техника.csv";
    private static final String ADDITIONAL_EQUIPMENT = "inputdata/Прицепное оборудование.csv";

    private static final String PEAS_PLAN = "inputdata/Горох.csv";
    private static final String SUGAR_BEET_PLAN = "inputdata/Сахарная свекла.csv";
    private static final String SPRING_WHEAT_PLAN = "inputdata/Яровая пшеница.csv";
    private static final String WINTER_WHEAT_PLAN = "inputdata/Озимая пшеница.csv";

    private static final String TYPE_AND_SHIFT_DEPENDENCY = "inputdata/Виды работ.csv";

    private static final String OUTPUT_STANDARDS = "inputdata/Нормы выработки.csv";



/*
    private static final List<String> stringFields = Helper.getStringsFromFile(FIELDS_PATH);
    private static final List<String> stringAllMachinery = Helper.getStringsFromFile(MACHINERY);
    private static final List<String> stringAllAdditionalEquipment = Helper.getStringsFromFile(ADDITIONAL_EQUIPMENT);
    private static final List<String> stringPeasPlan = Helper.getStringsFromFile(PEAS_PLAN);
    private static final List<String> stringSugarBeetPlan = Helper.getStringsFromFile(SUGAR_BEET_PLAN);
    private static final List<String> stringSpringWheatPlan = Helper.getStringsFromFile(SPRING_WHEAT_PLAN);
    private static final List<String> stringWinterWheatPlan = Helper.getStringsFromFile(WINTER_WHEAT_PLAN);
    private static final List<String> stringTypeAndShiftDependency = Helper.getStringsFromFile(TYPE_AND_SHIFT_DEPENDENCY);
    private static final List<String> stringOutputStandards = Helper.getStringsFromFile(OUTPUT_STANDARDS);
*/

    private static final List<String> stringFields = Data.stringFields();
    private static final List<String> stringAllMachinery = Data.stringAllMachinery();
    private static final List<String> stringAllAdditionalEquipment = Data.stringAllAdditionalEquipment();
    private static final List<String> stringPeasPlan = Data.stringPeasPlan();
    private static final List<String> stringSugarBeetPlan = Data.stringSugarBeetPlan();
    private static final List<String> stringSpringWheatPlan = Data.stringSpringWheatPlan();
    private static final List<String> stringWinterWheatPlan = Data.stringWinterWheatPlan();
    private static final List<String> stringTypeAndShiftDependency = Data.stringTypeAndShiftDependency();
    private static final List<String> stringOutputStandards = Data.stringOutputStandards();
    private static final List<String> ss = new ArrayList<>();




    public static List<String> getStringFields() {

        return stringFields;
    }

    public static List<String> getStringAllMachinery() {
        return stringAllMachinery;
    }

    public static List<String> getStringAllAdditionalEquipment() {
        return stringAllAdditionalEquipment;
    }

    public static List<String> getStringPeasPlan() {
        return stringPeasPlan;
    }

    public static List<String> getStringSugarBeetPlan() {
        return stringSugarBeetPlan;
    }

    public static List<String> getStringSpringWheatPlan() {
        return stringSpringWheatPlan;
    }

    public static List<String> getStringWinterWheatPlan() {
        return stringWinterWheatPlan;
    }

    public static List<String> getStringTypeAndShiftDependency() {
        return stringTypeAndShiftDependency;
    }

    public static List<String> getStringOutputStandards() {
        return stringOutputStandards;
    }
}
