package externalcode.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {
    public static List<String> getStringsFromFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDifferenceDays(Calendar d1, Calendar d2) {
        long diff = d2.getTimeInMillis() - d1.getTimeInMillis();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static void writeToFile(String stringToWrite, String fileName) {
        byte[] bytes = stringToWrite.getBytes();
        Path path = Paths.get(fileName);
        //Files.write(path, bytes, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}
