package gui;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Data {

    private static ArrayList<Crop> cropList = new ArrayList<>();


    public static ArrayList<String> stringOutputStandards = new ArrayList<>();
    public static ArrayList<String> listOfTypeAndShiftDependency = new ArrayList<>();

    private static Set<AgroProcedure> agroProcedures = new HashSet<>();

    private static ArrayList<Selfmoveable> tractorList = new ArrayList<>();
    private static ArrayList<TrailedMachine> trailedMachinesList = new ArrayList<>();


    private static HashMap<String, Integer> trailedMachinesRegistry = new HashMap<>();
    private static HashMap<String, Integer> tractorsRegistry = new HashMap<>();


    private static LinkedHashMap<String, Field> listOfField = new LinkedHashMap<>();

    public static void addTostringOutputStandards(String st) {
        stringOutputStandards.add(st);
    }
    public static ArrayList<String> getTostringOutputStandards() {
        return stringOutputStandards;
    }

    public static void addTolistOfTypeAndShiftDependency(String st) {
        listOfTypeAndShiftDependency.add(st);
    }
    public static ArrayList<String> getListOfTypeAndShiftDependency() {
        return listOfTypeAndShiftDependency;
    }

    public static ArrayList<Field> getArrayListOfFields() {
        return new ArrayList<Field>(listOfField.values());
    }

    public static void addProcedureToSet(AgroProcedure ag) {
        agroProcedures.add(ag);
    }
    public static Set<AgroProcedure> getPpocedureFromSet() {
        return agroProcedures;
    }

    public static void addToFieldList(String st, Field field) {
        listOfField.put(st, field);
    }

    public static Field getFromListOfFields(String st) {
        return listOfField.get(st);
    }

    public static HashMap<String, Field> getListOfField() {
        return listOfField;
    }

    public static void addToCropList(Crop harvestable) {
        cropList.add(harvestable);
    }

    public static int getLastIndexOfCropList() {
        return (cropList.size() - 1);
    }

    public static Harvestable getFromCropList(int i) {
        return cropList.get(i);
    }
    public static Crop getCropByString(String str) {
        Crop result = new Crop("ThereIsNoNameForIt");
        for (Crop cr: cropList) {
            if ( cr.getNameOfCrop().equals(str)) {
                result = cr;
            }
        }
        return result;
    }


    public static void addNumToTrailedMachinesRegistry(String st) {
        if (trailedMachinesRegistry.containsKey(st)) {
            int x = trailedMachinesRegistry.get(st);
            trailedMachinesRegistry.put(st, x++);
        } else {
            trailedMachinesRegistry.put(st, 1);
        }
    }

    public static int getFromTrailedMachinesRegistry(String s) {
        return trailedMachinesRegistry.get(s);
    }


    public static void addNumToTractorsRegistry(String st) {
        if (tractorsRegistry.containsKey(st)) {
            int x = tractorsRegistry.get(st);
            tractorsRegistry.put(st, x++);
        } else {
            tractorsRegistry.put(st, 1);
        }
    }

    public static int getFromTractorsRegistry(String s) {
        return tractorsRegistry.get(s);
    }

    public static ArrayList<Selfmoveable> getTractorList() {
        return tractorList;
    }

    public static ArrayList<TrailedMachine> getTrailedMachinesList() {
        return trailedMachinesList;
    }

    public static ArrayList<Crop> getCropList() {
        return cropList;
    }

    public static void addToTractorList(Selfmoveable sm) {
        tractorList.add(sm);
    }

    public static void addToTrailedMachinesList(TrailedMachine tr) {
        trailedMachinesList.add(tr);
    }



    public static List<String> stringFields() {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Field field : Data.getArrayListOfFields()) {
            sb.append(field.getNameId());
            sb.append(";");
            sb.append(field.getArea());
            sb.append(";");
            sb.append(field.getCrop());
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> stringAllMachinery() {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Selfmoveable sf : Data.getTractorList()) {
            sb.append(sf.getName());
            sb.append(";");
            sb.append(sf.getType());
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> stringAllAdditionalEquipment() {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (TrailedMachine tm : Data.getTrailedMachinesList()) {
            sb.append(tm.getName());
            sb.append(";");
            sb.append(tm.getType());
            result.add(sb.toString());
        }
        return result;
    }

    public static List<String> stringPeasPlan() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < Data.getCropList().size(); i++) {
            if (Data.getCropList().get(i).getNameOfCrop().equals("Горох")) {
                for (int ii = 0; ii < Data.getCropList().get(i).getListOfProcedures().size(); ii++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Data.getCropList().get(i).getListOfProcedures().get(ii).getName());
                    sb.append(";");

                    Calendar c1 = Data.getCropList().get(i).getListOfProcedures().get(ii).getStartOfProcedure();
                    LocalDate lc1 = LocalDateTime.ofInstant(c1.toInstant(), c1.getTimeZone().toZoneId()).toLocalDate();
                    if (lc1.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc1.getDayOfMonth());
                    }
                    else {
                        sb.append(lc1.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc1.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc1.getMonthValue());
                    }
                    else {
                        sb.append(lc1.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc1.getYear());
                    sb.append(";");


                    Calendar c2 = Data.getCropList().get(i).getListOfProcedures().get(ii).getEndOfProcedure();
                    LocalDate lc2 = LocalDateTime.ofInstant(c2.toInstant(), c2.getTimeZone().toZoneId()).toLocalDate();
                    if (lc2.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc2.getDayOfMonth());
                    }
                    else {
                        sb.append(lc2.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc2.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc2.getMonthValue());
                    }
                    else {
                        sb.append(lc2.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc2.getYear());
                    result.add(sb.toString());
                    sb = null;
                }
            }


        }
        return result;
    }

    public static List<String> stringSugarBeetPlan() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < Data.getCropList().size(); i++) {
            if (Data.getCropList().get(i).getNameOfCrop().equals("Сахарная свекла")) {
                for (int ii = 0; ii < Data.getCropList().get(i).getListOfProcedures().size(); ii++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Data.getCropList().get(i).getListOfProcedures().get(ii).getName());
                    sb.append(";");

                    Calendar c1 = Data.getCropList().get(i).getListOfProcedures().get(ii).getStartOfProcedure();
                    LocalDate lc1 = LocalDateTime.ofInstant(c1.toInstant(), c1.getTimeZone().toZoneId()).toLocalDate();
                    if (lc1.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc1.getDayOfMonth());
                    }
                    else {
                        sb.append(lc1.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc1.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc1.getMonthValue());
                    }
                    else {
                        sb.append(lc1.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc1.getYear());
                    sb.append(";");


                    Calendar c2 = Data.getCropList().get(i).getListOfProcedures().get(ii).getEndOfProcedure();
                    LocalDate lc2 = LocalDateTime.ofInstant(c2.toInstant(), c2.getTimeZone().toZoneId()).toLocalDate();
                    if (lc2.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc2.getDayOfMonth());
                    }
                    else {
                        sb.append(lc2.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc2.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc2.getMonthValue());
                    }
                    else {
                        sb.append(lc2.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc2.getYear());
                    result.add(sb.toString());
                    sb = null;

                }
            }

        }

        return result;
    }

    public static List<String> stringSpringWheatPlan() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < Data.getCropList().size(); i++) {
            if (Data.getCropList().get(i).getNameOfCrop().equals("Озимая пшеница")) {
                for (int ii = 0; ii < Data.getCropList().get(i).getListOfProcedures().size(); ii++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Data.getCropList().get(i).getListOfProcedures().get(ii).getName());
                    sb.append(";");

                    Calendar c1 = Data.getCropList().get(i).getListOfProcedures().get(ii).getStartOfProcedure();
                    LocalDate lc1 = LocalDateTime.ofInstant(c1.toInstant(), c1.getTimeZone().toZoneId()).toLocalDate();
                    if (lc1.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc1.getDayOfMonth());
                    }
                    else {
                        sb.append(lc1.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc1.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc1.getMonthValue());
                    }
                    else {
                        sb.append(lc1.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc1.getYear());
                    sb.append(";");


                    Calendar c2 = Data.getCropList().get(i).getListOfProcedures().get(ii).getEndOfProcedure();
                    LocalDate lc2 = LocalDateTime.ofInstant(c2.toInstant(), c2.getTimeZone().toZoneId()).toLocalDate();
                    if (lc2.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc2.getDayOfMonth());
                    }
                    else {
                        sb.append(lc2.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc2.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc2.getMonthValue());
                    }
                    else {
                        sb.append(lc2.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc2.getYear());
                    result.add(sb.toString());
                    sb = null;

                }
            }

        }

        return result;
    }

    public static List<String> stringWinterWheatPlan() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Data.getCropList().size(); i++) {
            if (Data.getCropList().get(i).getNameOfCrop().equals("Яровая пшеница")) {
                for (int ii = 0; ii < Data.getCropList().get(i).getListOfProcedures().size(); ii++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Data.getCropList().get(i).getListOfProcedures().get(ii).getName());
                    sb.append(";");

                    Calendar c1 = Data.getCropList().get(i).getListOfProcedures().get(ii).getStartOfProcedure();
                    LocalDate lc1 = LocalDateTime.ofInstant(c1.toInstant(), c1.getTimeZone().toZoneId()).toLocalDate();
                    if (lc1.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc1.getDayOfMonth());
                    }
                    else {
                        sb.append(lc1.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc1.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc1.getMonthValue());
                    }
                    else {
                        sb.append(lc1.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc1.getYear());
                    sb.append(";");


                    Calendar c2 = Data.getCropList().get(i).getListOfProcedures().get(ii).getEndOfProcedure();
                    LocalDate lc2 = LocalDateTime.ofInstant(c2.toInstant(), c2.getTimeZone().toZoneId()).toLocalDate();
                    if (lc2.getDayOfMonth() < 10) {
                        sb.append("0");
                        sb.append(lc2.getDayOfMonth());
                    }
                    else {
                        sb.append(lc2.getDayOfMonth());
                    }
                    sb.append(".");
                    if (lc2.getMonthValue()<10) {
                        sb.append("0");
                        sb.append(lc2.getMonthValue());
                    }
                    else {
                        sb.append(lc2.getMonthValue());
                    }
                    sb.append(".");
                    sb.append(lc2.getYear());
                    result.add(sb.toString());
                    sb = null;

                }
            }

        }
        return result;
    }

    public static List<String> stringTypeAndShiftDependency() {
        return Data.getListOfTypeAndShiftDependency();
    }

    public static List<String> stringOutputStandards() {
        return Data.getTostringOutputStandards();
    }


}




