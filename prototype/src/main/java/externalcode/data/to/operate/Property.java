package externalcode.data.to.operate;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Property {
    private static final List<Field> ALL_FIELDS = initAllFields();
    private static final Map<String, String> ALL_MACHINERY = initAllMachinery();
    private static final Map<String, String> ALL_EQUIPMENT = initAllEquipment();

    // Receiving total square for all fields
    private static final double TOTAL_SQUARE_OF_ALL_FIELDS = ALL_FIELDS.stream().mapToDouble(Field::getSquare).sum();

    // Receiving total square for all peas fields
    private static final double TOTAL_SQUARE_OF_PEAS_FIELDS = ALL_FIELDS.stream()
            .filter(f -> f.getCrop() == Crop.PEAS).mapToDouble(Field::getSquare).sum();

    // Receiving total square for all sugar beet fields
    private static final double TOTAL_SQUARE_OF_SUGAR_BEET_FIELDS = ALL_FIELDS.stream()
            .filter(f -> f.getCrop() == Crop.SUGAR_BEET).mapToDouble(Field::getSquare).sum();

    // Receiving total square for all spring wheat fields
    private static final double TOTAL_SQUARE_OF_SPRING_WHEAT_FIELDS = ALL_FIELDS.stream()
            .filter(f -> f.getCrop() == Crop.SPRING_WHEAT).mapToDouble(Field::getSquare).sum();

    // Receiving total square for all winter wheat fields
    private static final double TOTAL_SQUARE_OF_WINTER_WHEAT_FIELDS = ALL_FIELDS.stream()
            .filter(f -> f.getCrop() == Crop.WINTER_WHEAT).mapToDouble(Field::getSquare).sum();

    public static List<Field> getAllFields() {
        return ALL_FIELDS;
    }

    public static Map<String, String> getAllMachinery() {
        return ALL_MACHINERY;
    }

    public static Map<String, String> getAllEquipment() {
        return ALL_EQUIPMENT;
    }

    public static double getTotalSquareOfAllFields() {
        return TOTAL_SQUARE_OF_ALL_FIELDS;
    }

    public static double getTotalSquareOfPeasFields() {
        return TOTAL_SQUARE_OF_PEAS_FIELDS;
    }

    public static double getTotalSquareOfSugarBeetFields() {
        return TOTAL_SQUARE_OF_SUGAR_BEET_FIELDS;
    }

    public static double getTotalSquareOfSpringWheatFields() {
        return TOTAL_SQUARE_OF_SPRING_WHEAT_FIELDS;
    }

    public static double getTotalSquareOfWinterWheatFields() {
        return TOTAL_SQUARE_OF_WINTER_WHEAT_FIELDS;
    }

    private static List<Field> initAllFields() {
        List<String> list = InputStorage.getStringFields();
        List<Field> fields = new ArrayList<>();
        for (String stringField : list) {
            String[] splittedStringField = stringField.split(";");
//            System.out.println(Arrays.toString(splittedStringField));
            if (splittedStringField.length == 3) {
                fields.add(new Field(splittedStringField[0].trim(),
                        Double.parseDouble(splittedStringField[1].trim()),
                        addCrop(splittedStringField[2].trim())));
            }
        }
        return fields;
    }

    private static Crop addCrop(String crop) {
        String input = crop.toLowerCase();
        switch (input) {
            case "пшеница яровая":
                return Crop.SPRING_WHEAT;
            case "пшеница озимая":
                return Crop.WINTER_WHEAT;
            case "горох":
                return Crop.PEAS;
            case "свекла сахарная":
                return Crop.SUGAR_BEET;
            default:
                return null;
        }
    }

    private static Map<String, String> initAllMachinery() {
        List<String> list = InputStorage.getStringAllMachinery();
        return makeMap(list);
    }

    private static Map<String, String> initAllEquipment() {
        List<String> list = InputStorage.getStringAllAdditionalEquipment();
        return makeMap(list);
    }


    private static Map<String, String> makeMap(List<String> list) {
        Map<String, String> map = new HashMap<>();
        for (String s : list) {
            String[] splittedS = s.split(";");
//            System.out.println(Arrays.toString(splittedS));
            if (splittedS.length == 2) {
                map.put(splittedS[0].trim(), splittedS[1].trim());
            }
        }
        return map;
    }
}
