import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {

    public static LocalDate getLastWorkDay() {
        LocalDate local = LocalDate.now();
        LocalDate lastOfMonth = local.with(TemporalAdjusters.lastDayOfMonth());
        if (lastOfMonth.getDayOfWeek().equals(DayOfWeek.SATURDAY) || lastOfMonth.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            lastOfMonth = lastOfMonth.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        }

        return lastOfMonth;
    }

    public static Map<String, String> paramGetter(String fileLocation) {
        Map<String, String> params = new HashMap<>();

        File f = new File(fileLocation);
        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] part = line.split(" = ");
                String key = part[0];
                String value = part[1];
                params.put(key, value);
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return params;

    }

}