import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class Utils {

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

    public static LocalDate getLastWorkDay() throws IOException {
        LocalDate local = LocalDate.now();
        LocalDate lastOfMonth = local.with(TemporalAdjusters.lastDayOfMonth());
        while (! isWorkingDay(lastOfMonth)) {
            lastOfMonth = lastOfMonth.minusDays(1);
        }
        return lastOfMonth;
    }

    private static boolean isWorkingDay (LocalDate date) throws IOException {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            System.out.println(date + " IS SATURDAY");
            return false;
        }
        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            System.out.println(date + " IS SUNDAY");
            return false;
        }
        if (isHoliday(date)) {
            System.out.println(date + " IS HOLIDAY");
            return false;
        }
        return true;
    }

    private static boolean isHoliday (LocalDate date) throws IOException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Holiday> holidays = getHolidayList("https://date.nager.at/api/v2/publicholidays/" + year + "/PT");
        for (Holiday h : holidays) {
            String d = h.getDate();
            List<String> county = h.getCounties();
            LocalDate hDate = stringToDate(d);
            if (hDate.equals(date) && county == null) {
                return true;
            }
        }
        return false;
    }

    private static LocalDate stringToDate (String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }

    private static String getJSON (String stringUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        URL url = new URL(stringUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if(conn.getResponseCode() == 200) {
            Scanner scan = new Scanner(url.openStream());
            while(scan.hasNext()) {
                sb.append(scan.nextLine());
            }
        }
        return sb.toString();
    }

    private static List<Holiday> getHolidayList (String url) throws IOException {
        String jsonArray = Utils.getJSON(url);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Holiday> list = objectMapper.readValue(jsonArray, new TypeReference<List<Holiday>>(){});
        return list;
    }

}