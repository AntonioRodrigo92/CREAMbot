import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class UtilsTest {

    @Test
    void paramGetter() {

    }

    @Test
    void getLastWorkDay() {
        try {
            LocalDate lastWorkingDay = Utils.getLastWorkDay();
            LocalDate lastWorkingDayTest = LocalDate.of(2022, 2, 28);
            LocalDate lastWorkingDayTestFailure = LocalDate.of(2022, 2, 27);
            Assertions.assertEquals(lastWorkingDay, lastWorkingDayTest);
            Assertions.assertNotEquals(lastWorkingDay, lastWorkingDayTestFailure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
